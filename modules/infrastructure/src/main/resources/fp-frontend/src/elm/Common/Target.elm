module Common.Target exposing (fpTarget)

import Html exposing (..)
import Html.Attributes exposing (..)


fpTarget : Html msg
fpTarget =
    aside
        [ class "box fp-system-title" ]
        [ text "〇〇システム ver1.2" ]
