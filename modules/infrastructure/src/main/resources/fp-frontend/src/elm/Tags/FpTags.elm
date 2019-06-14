module Tags.FpTags exposing (main_, script)

import Html exposing (..)
import Html.Attributes exposing (..)


script : List (Attribute msg) -> List (Html msg) -> Html msg
script =
    node "script"


main_ : List (Attribute msg) -> List (Html msg) -> Html msg
main_ =
    node "main"
