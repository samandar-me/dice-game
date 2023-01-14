package com.sdk.dicegame.presentation.home

import com.sdk.dicegame.R

data class GameState(
    var player1Score: Int = 0,
    var player2Score: Int = 0,
    var currentScore: Int = 0,
    var im1: Int = R.drawable.dice1,
    var im2: Int = R.drawable.dice1,
    val isGameFinished: Boolean = false,
    var isButton1Enabled: Boolean = true,
    var isButton2Enabled: Boolean = false,
    var rotation: Float = 306f
)