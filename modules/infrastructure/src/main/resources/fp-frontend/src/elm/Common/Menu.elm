module Common.Menu exposing (Msg(..), fpMenu)

import Html exposing (..)
import Html.Attributes exposing (..)


type Msg
    = FunctionPointCalc
    | System
    | User


isActive : Msg -> Msg -> String
isActive targetMenu selectedMenu =
    if targetMenu == selectedMenu then
        "is-active"

    else
        ""


fpMenu : Msg -> Html msg
fpMenu selectedMenu =
    div
        [ class "submenu column is-3" ]
        [ br
            []
            []
        , aside
            [ class "box fp-menu" ]
            [ p
                [ class "menu-label" ]
                [ text "ファンクションポイント計算" ]
            , ul
                [ class "menu-list" ]
                [ li
                    []
                    [ a
                        [ class
                            (isActive FunctionPointCalc selectedMenu)
                        , href "#"
                        ]
                        [ text "ファンクションポイント計算" ]
                    ]
                , li
                    []
                    [ a
                        [ class
                            (isActive System selectedMenu)
                        , href "#"
                        ]
                        [ text "システム情報登録" ]
                    ]
                ]
            , p
                [ class "menu-label" ]
                [ text "マスタ" ]
            , ul
                [ class "menu-list" ]
                [ li
                    []
                    [ a
                        [ class
                            (isActive User selectedMenu)
                        , href "#"
                        ]
                        [ text "ユーザー設定" ]
                    ]
                ]
            ]
        ]
