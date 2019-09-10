module System.System exposing (Model, Msg(..), init, main, update, view)

import Browser
import Browser.Navigation as Nav
import Common.Content as Content exposing (fpContent)
import Common.Menu as Menu exposing (Msg)
import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events as Events exposing (on, onClick, onInput)
import Http
import Json.Decode as D exposing (Decoder)
import Json.Encode as E
import Url


main : Program () Model Msg
main =
    Browser.application
        { init = init
        , view = view
        , update = update
        , subscriptions = \_ -> Sub.none
        , onUrlChange = UrlChanged
        , onUrlRequest = UrlRequest
        }


type alias Model =
    { systemId : Int
    , systemName : String
    , subSystemId : Int
    , subSystemName : String
    , subSystemList : List SubSystem
    , message : String
    , operationState : OperationState
    , key : Nav.Key
    , url : Url.Url
    }


type alias SystemSetting =
    { systemId : Int
    , systemName : String
    , subSystemList : List SubSystem
    }


type alias SubSystem =
    { subSystemId : Int
    , subSystemName : String
    }


type OperationState
    = Init
    | Waiting
    | Loaded SystemSetting
    | Failed Http.Error


init : () -> Url.Url -> Nav.Key -> ( Model, Cmd Msg )
init flags url key =
    ( { systemId = 0
      , systemName = ""
      , subSystemId = 0
      , subSystemName = ""
      , subSystemList = [ { subSystemId = 1, subSystemName = "A" }, { subSystemId = 2, subSystemName = "BB" } ]
      , message = ""
      , operationState = Init
      , key = key
      , url = url
      }
    , Cmd.none
    )


type Msg
    = InputSystemName String
    | ChangeSubSystem String
    | ModSubSystem Int
    | SystemSettingUpdate
    | Receive (Result Http.Error SystemSetting)
    | UrlChanged Url.Url
    | UrlRequest Browser.UrlRequest


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        InputSystemName systemName ->
            ( { model | systemName = systemName }, Cmd.none )

        ChangeSubSystem subSystem ->
            ( { model | subSystemName = subSystem }, Cmd.none )

        ModSubSystem subSystemId ->
            ( { model | subSystemId = subSystemId }, Cmd.none )

        SystemSettingUpdate ->
            ( { model | message = "", operationState = Waiting }
            , Http.post
                { url = "/system/update"
                , body = Http.jsonBody <| systemSettingEncode <| createSystemSetting model
                , expect = Http.expectJson Receive systemSettingDecoder
                }
            )

        Receive (Ok systemSetting) ->
            ( { model | operationState = Loaded systemSetting }, Cmd.none )

        Receive (Err e) ->
            ( { model | message = "処理に失敗!!!", operationState = Failed e }, Cmd.none )

        UrlRequest urlRequest ->
            case urlRequest of
                Browser.Internal url ->
                    ( model, Nav.pushUrl model.key (Url.toString url) )

                Browser.External href ->
                    ( model, Nav.load href )

        UrlChanged url ->
            ( { model | url = url }, Cmd.none )


view : Model -> Browser.Document Msg
view model =
    let
        subSystemHandler selectedValue =
            ChangeSubSystem selectedValue
    in
    { title = "FP システム情報登録"
    , body =
        [ Html.form
            []
            [ Content.fpContent Menu.System <|
                section
                    [ class "container" ]
                    [ div
                        [ class "columns is-multiline" ]
                        [ div
                            [ class "column is-one-third" ]
                            [ div
                                [ class "field" ]
                                [ div
                                    [ class "control" ]
                                    [ label
                                        [ class "label" ]
                                        [ text "システム名" ]
                                    , input
                                        [ class "input", type_ "text", placeholder "システム名", value model.systemName, onInput InputSystemName ]
                                        []
                                    ]
                                , br [] []
                                , br [] []
                                , div
                                    [ class "control" ]
                                    [ label
                                        [ class "label" ]
                                        [ text "サブシステム" ]
                                    , div
                                        [ class "control" ]
                                        [ div
                                            [ class "select" ]
                                            [ select
                                                [ id "fp-subsystem-select", onChange subSystemHandler ]
                                                (List.map
                                                    (\opt -> option [] [ text opt.subSystemName ])
                                                    model.subSystemList
                                                )
                                            ]
                                        ]
                                    ]
                                , br [] []
                                , div
                                    [ class "control" ]
                                    [ label
                                        [ class "label" ]
                                        [ text "サブシステム名" ]
                                    , div
                                        [ class "field has-addons" ]
                                        [ div
                                            [ class "control" ]
                                            [ input
                                                [ class "input", type_ "text", placeholder "サブシステム名", value model.subSystemName ]
                                                []
                                            ]
                                        , div
                                            [ class "control" ]
                                            [ button
                                                [ class "button is-info", type_ "button", onClick (ModSubSystem model.subSystemId) ]
                                                [ text "設定" ]
                                            ]
                                        ]
                                    ]
                                , div
                                    [ class "control column is-4 is-offset-10" ]
                                    [ div
                                        [ class "field" ]
                                        [ div
                                            [ class "control" ]
                                            [ button
                                                [ class "button is-primary", type_ "button", onClick SystemSettingUpdate ]
                                                [ text "登録" ]
                                            ]
                                        ]
                                    ]
                                ]
                            , br [] []
                            , div
                                [ class "field" ]
                                [ p
                                    [ class "has-text-danger" ]
                                    [ text model.message ]
                                ]
                            ]
                        ]
                    ]
            ]
        ]
    }


onChange : (String -> msg) -> Attribute msg
onChange handler =
    on "change" (D.map handler Events.targetValue)



-- encode/decode


createSystemSetting : Model -> SystemSetting
createSystemSetting m =
    { systemId = m.systemId
    , systemName = m.systemName
    , subSystemList = m.subSystemList
    }


systemSettingEncode : SystemSetting -> E.Value
systemSettingEncode u =
    E.object
        [ ( "systemId", E.int u.systemId )
        , ( "systemName", E.string u.systemName )
        , ( "subSystemList", E.list subSystemEncode u.subSystemList )
        ]


subSystemEncode : SubSystem -> E.Value
subSystemEncode u =
    E.object
        [ ( "subSystemId", E.int u.subSystemId )
        , ( "subSystemName", E.string u.subSystemName )
        ]


systemSettingDecoder : Decoder SystemSetting
systemSettingDecoder =
    D.map3 SystemSetting
        (D.field "systemId" D.int)
        (D.field "systemName" D.string)
        (D.field "subSystemList" (D.list subSystemDecoder))


subSystemDecoder : Decoder SubSystem
subSystemDecoder =
    D.map2 SubSystem
        (D.field "subSystemId" D.int)
        (D.field "subSystemName" D.string)
