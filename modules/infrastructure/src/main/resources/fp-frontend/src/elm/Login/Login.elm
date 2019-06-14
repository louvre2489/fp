port module Login.Login exposing (LoginUser, Model, Msg(..), OperationState(..), createLoginUser, init, loginDecoder, loginEncode, main, portSetLocalStorage, update, view)

import Browser
import Browser.Navigation as Nav
import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick, onInput)
import Http
import Json.Decode as D exposing (Decoder)
import Json.Encode as E
import Url


port portSetLocalStorage : String -> Cmd msg


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
    { userId : String
    , password : String
    , message : String
    , operationState : OperationState
    , key : Nav.Key
    , url : Url.Url
    }


type OperationState
    = Init
    | Waiting
    | Loaded LoginUser
    | Failed Http.Error


type alias LoginUser =
    { userId : String
    , password : String
    , isLoginSuccess : Bool
    , token : String
    }


init : () -> Url.Url -> Nav.Key -> ( Model, Cmd Msg )
init flags url key =
    ( { userId = ""
      , password = ""
      , message = ""
      , operationState = Init
      , key = key
      , url = url
      }
    , Cmd.none
    )


type Msg
    = InputUserId String
    | InputPassword String
    | Login
    | Send
    | Receive (Result Http.Error LoginUser)
    | UrlChanged Url.Url
    | UrlRequest Browser.UrlRequest


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        InputUserId userId ->
            ( { model | userId = userId }, Cmd.none )

        InputPassword password ->
            ( { model | password = password }, Cmd.none )

        Login ->
            ( { model | password = "", message = "", operationState = Waiting }
            , Http.post
                { url = "/login"
                , body = Http.jsonBody <| loginEncode <| createLoginUser model
                , expect = Http.expectJson Receive loginDecoder
                }
            )

        Send ->
            ( { model | password = "", operationState = Waiting }
            , Http.post
                { url = "/login"
                , body = Http.jsonBody <| loginEncode <| createLoginUser model
                , expect = Http.expectJson Receive loginDecoder
                }
            )

        Receive (Ok user) ->
            case user.isLoginSuccess of
                True ->
                    ( { model | operationState = Loaded user }, Cmd.batch [ portSetLocalStorage user.token, Nav.load "/system" ] )

                False ->
                    ( { model | message = "ログインに失敗しました。", operationState = Loaded user }, Cmd.none )

        Receive (Err e) ->
            ( { model | message = "ログインに失敗しました。", operationState = Failed e }, Cmd.none )

        UrlRequest urlRequest ->
            case urlRequest of
                Browser.Internal url ->
                    ( model, Nav.pushUrl model.key (Url.toString url) )

                Browser.External href ->
                    ( model, Nav.load href )

        UrlChanged url ->
            ( { model | url = url }, Cmd.none )



-- view : Model -> Html Msg


view : Model -> Browser.Document Msg
view model =
    { title = "FP ログイン"
    , body =
        [ Html.form
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
                                    [ class "input", type_ "password", placeholder "パスワード", value model.password, onInput InputPassword ]
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
                            , p
                                [ class "has-text-danger" ]
                                [ text model.message ]
                            ]
                        ]
                    ]
                ]
            ]
        ]
    }



-- encode/decode


createLoginUser : Model -> LoginUser
createLoginUser m =
    { userId = m.userId
    , password = m.password
    , isLoginSuccess = False
    , token = ""
    }


loginEncode : LoginUser -> E.Value
loginEncode u =
    E.object
        [ ( "userId", E.string u.userId )
        , ( "password", E.string u.password )
        , ( "isLoginSuccess", E.bool u.isLoginSuccess )
        , ( "token", E.string u.token )
        ]


loginDecoder : Decoder LoginUser
loginDecoder =
    D.map4 LoginUser
        (D.field "userId" D.string)
        (D.field "password" D.string)
        (D.field "isLoginSuccess" D.bool)
        (D.field "token" D.string)
