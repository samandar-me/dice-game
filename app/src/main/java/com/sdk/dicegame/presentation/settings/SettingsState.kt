package com.sdk.dicegame.presentation.settings

data class SettingsState(
    val firstStart: Boolean = false,
    val playWith: Boolean = false,
    val sound: Boolean = false,
    val finishCount: Boolean = false
)
