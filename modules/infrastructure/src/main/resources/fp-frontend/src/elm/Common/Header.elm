module Common.Header exposing (fpHeader)

import Html exposing (..)
import Html.Attributes exposing (..)


fpHeader : Html msg
fpHeader =
    header
        [ class "nav" ]
        [ div
            [ class "hero is-info" ]
            [ div
                [ id "fp-hero", class "hero-body" ]
                [ div
                    [ id "fp-hero_title", class "container" ]
                    [ h1
                        [ class "title" ]
                        [ text "Function Point 計算機" ]
                    ]
                ]
            ]
        ]
