package com.sdk.dicegame.presentation.computer

sealed class ComputerEvent {
    object PlayerClicked: ComputerEvent()
    object ComputerClicked: ComputerEvent()
    object OkButtonClicked: ComputerEvent()
}