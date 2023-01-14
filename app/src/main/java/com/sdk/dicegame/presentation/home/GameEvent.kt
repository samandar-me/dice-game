package com.sdk.dicegame.presentation.home

sealed class GameEvent {
    object Player1Clicked: GameEvent()
    object Player2Clicked: GameEvent()
}