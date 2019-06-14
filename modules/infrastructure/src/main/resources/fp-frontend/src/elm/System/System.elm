module System.System exposing (Model, Msg(..), init, main, update, view)

import Browser
import Browser.Navigation as Nav
import Common.Content as Content exposing (fpContent)
import Common.Header as Header exposing (fpHeader)
import Common.Menu as Menu exposing (Msg, fpMenu)
import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick, onInput)
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
    { key : Nav.Key
    , url : Url.Url
    }


init : () -> Url.Url -> Nav.Key -> ( Model, Cmd Msg )
init flags url key =
    ( { key = key
      , url = url
      }
    , Cmd.none
    )


type Msg
    = UrlChanged Url.Url
    | UrlRequest Browser.UrlRequest


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
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
    { title = "FP システム情報登録"
    , body =
        [ Html.form
            []
            [ Header.fpHeader
            , Content.fpContent Menu.System <|
                p [] [ text "このブロックにメインのviewを記述する" ]
            ]
        ]
    }
