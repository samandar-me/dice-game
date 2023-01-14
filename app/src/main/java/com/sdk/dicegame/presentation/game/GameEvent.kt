package com.sdk.dicegame.presentation.game

sealed class GameEvent {
    object Player1Clicked: GameEvent()
    object Player2Clicked: GameEvent()
    object OkButtonClicked: GameEvent()
}