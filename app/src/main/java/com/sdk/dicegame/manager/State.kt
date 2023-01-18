package com.sdk.dicegame.manager

data class State(
    val firstStart: Boolean = false,
    val playWith: Boolean = false,
    val sound: Boolean = false,
    val finishCount: Boolean = false,
)