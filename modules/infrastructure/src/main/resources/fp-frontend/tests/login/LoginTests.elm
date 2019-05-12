module LoginTests exposing (updateTest)

import Expect exposing (Expectation)
import Login exposing (..)
import Test exposing (..)


updateTest : Test
updateTest =
    describe "ログインのテスト"
        [ describe "update"
            [ test "ユーザーIDの入力" <|
                \_ ->
                    let
                        msg =
                            InputUserId "USER_ID"

                        model =
                            { userId = "", password = "", operationState = Init }

                        result =
                            { userId = "USER_ID", password = "", operationState = Init }
                    in
                    model
                        |> update msg
                        |> (\( m, c ) ->
                                m
                           )
                        |> Expect.equal result
            ]
        ]
