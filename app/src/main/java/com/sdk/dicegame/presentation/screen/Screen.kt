package com.sdk.dicegame.presentation.screen

sealed class Screen(val route: String) {
    object SplashScreen: Screen(
        route = "Splash"
    )
    object StartScreen: Screen(
        route = "Start"
    )
    object HomeScreen: Screen(
        route = "Game"
    )
}