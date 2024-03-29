package com.sdk.dicegame.presentation.game

import com.sdk.dicegame.R

data class GameState(
    var player1Score: Int = 0,
    var player2Score: Int = 0,
    var currentScore: Int = 0,
    var im1: Int = R.drawable.dice1,
    var im2: Int = R.drawable.dice1,
    var isButton1Enabled: Boolean = false,
    var isButton2Enabled: Boolean = false
)