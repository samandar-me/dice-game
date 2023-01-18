package com.sdk.dicegame.presentation.settings

sealed class SettingsEvent {
    object OnFirstStart: SettingsEvent()
    object OnPlayWith: SettingsEvent()
    object OnSound: SettingsEvent()
    object OnFinishCount: SettingsEvent()
}