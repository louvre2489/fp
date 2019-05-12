module Example exposing (description, suite)

import Expect exposing (Expectation)
import Fuzz exposing (Fuzzer, int, list, string)
import Test exposing (..)


suite : Test
suite =
    describe "Example Test Suite"
        [ test "should equal" <|
            \() ->
                description
                    |> Expect.equal "Elm is functional language"
        ]


description : String
description =
    "Elm is functional language"
