package com.sdk.dicegame.presentation.computer

import com.sdk.dicegame.R

data class ComputerState(
    var playerScore: Int = 0,
    var computerScore: Int = 0,
    var currentScore: Int = 0,
    var im1: Int = R.drawable.dice1,
    var im2: Int = R.drawable.dice1,
    var isButton1Enabled: Boolean = true,
    var isButton2Enabled: Boolean = false
)