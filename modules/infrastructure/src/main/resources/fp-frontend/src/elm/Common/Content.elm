module Common.Content exposing (fpContent)

import Common.Menu as Menu exposing (Msg(..), fpMenu)
import Common.Target as Target exposing (fpTarget)
import Html exposing (..)
import Html.Attributes exposing (..)
import Tags.FpTags as FpTags exposing (main_)


fpContent : Menu.Msg -> Html msg -> Html msg
fpContent selectedMenu mainContent =
    FpTags.main_
        [ class "columns" ]
        [ Menu.fpMenu selectedMenu
        , div
            [ class "column" ]
            [ br
                []
                []
            , Target.fpTarget
            , aside
                [ class "box fp-content-main" ]
                [ div
                    [ id "fp-content" ]
                    [ mainContent ]
                ]
            ]
        ]
