module Login exposing (Model, Msg(..), OperationState(..), main, update)

import Browser
import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick, onInput)
import Http
import Json.Decode as D exposing (Decoder)
import Json.Encode as E


main : Program () Model Msg
main =
    Browser.element
        { init = init
        , view = view
        , update = update
        , subscriptions = \_ -> Sub.none
        }



-- Model


type alias Model =
    { userId : String
    , password : String
    , operationState : OperationState
    }


type OperationState
    = Init
    | Waiting
    | Loaded LoginUser
    | Failed Http.Error


type alias LoginUser =
    { userId : String
    , password : String
    }


init : () -> ( Model, Cmd Msg )
init _ =
    ( { userId = ""
      , password = ""
      , operationState = Init
      }
    , Cmd.none
    )


type Msg
    = InputUserId String
    | InputPassword String
    | Login
    | Send
    | Receive (Result Http.Error LoginUser)


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        InputUserId userId ->
            ( { model | userId = userId }, Cmd.none )

        InputPassword password ->
            ( { model | password = password }, Cmd.none )

        Login ->
            ( { model | password = "", operationState = Waiting }
            , Http.post
                { url = "/login"
                , body = Http.jsonBody <| loginEncode <| createLoginUser model
                , expect = Http.expectJson Receive loginDecoder
                }
            )

        Send ->
            -- TODO Submit
            ( { model | password = "", operationState = Waiting }
            , Http.post
                { url = "/login"
                , body = Http.jsonBody <| loginEncode <| createLoginUser model
                , expect = Http.expectJson Receive loginDecoder
                }
            )

        Receive (Ok user) ->
            ( { model | operationState = Loaded user }, Cmd.none )

        Receive (Err e) ->
            ( { model | operationState = Failed e }, Cmd.none )


view : Model -> Html Msg
view model =
    Html.form
        []
        [ section
            [ class "section" ]
            [ div
                [ class "container" ]
                [ div
                    [ class "column is-one-third" ]
                    [ h1
                        [ class "title" ]
                        [ figure
                            [ class "image is-128x128" ]
                            [ img
                                [ class "is-rounded", src "/public/image/login.png" ]
                                []
                            ]
                        , p
                            [ style "font-size" "2rem" ]
                            [ text "〜画像は変える〜" ]
                        ]
                    , div
                        [ class "field" ]
                        [ div
                            [ class "control" ]
                            [ label
                                [ class "label" ]
                                [ text "ユーザーID" ]
                            , input
                                [ class "input", type_ "text", placeholder "ユーザーID", value model.userId, onInput InputUserId ]
                                []
                            ]
                        ]
                    , div
                        [ class "field" ]
                        [ div
                            [ class "control" ]
                            [ label
                                [ class "label" ]
                                [ text "パスワード" ]
                            , input
                                [ class "input", type_ "text", placeholder "パスワード", value model.password, onInput InputPassword ]
                                []
                            ]
                        ]
                    , div
                        [ class "field" ]
                        [ div
                            [ class "control" ]
                            [ button
                                [ class "button is-primary", type_ "button", onClick Login ]
                                [ text "ログイン" ]
                            ]
                        ]
                    ]
                ]
            ]
        ]



-- encode/decode


createLoginUser : Model -> LoginUser
createLoginUser m =
    { userId = m.userId
    , password = m.password
    }


loginEncode : LoginUser -> E.Value
loginEncode u =
    E.object
        [ ( "userId", E.string u.userId )
        , ( "password", E.string u.password )
        ]


loginDecoder : Decoder LoginUser
loginDecoder =
    D.map2 LoginUser
        (D.field "userId" D.string)
        (D.field "password" D.string)
