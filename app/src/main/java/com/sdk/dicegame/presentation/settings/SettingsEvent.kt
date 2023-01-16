package com.sdk.dicegame.presentation.settings

sealed class SettingsEvent {
    data class OnFirstStart(val boolean: Boolean): SettingsEvent()
    data class OnPlayWith(val boolean: Boolean): SettingsEvent()
    data class OnSound(val boolean: Boolean): SettingsEvent()
    data class OnFinishCount(val boolean: Boolean): SettingsEvent()
}