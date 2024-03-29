package com.sdk.dicegame

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sdk.dicegame.presentation.computer.ComputerScreen
import com.sdk.dicegame.presentation.game.GameScreen
import com.sdk.dicegame.presentation.intro_page.IntroScreen
import com.sdk.dicegame.presentation.intro_page.SplashScreen
import com.sdk.dicegame.presentation.screen.Screen
import com.sdk.dicegame.presentation.settings.SettingScreen
import com.sdk.dicegame.presentation.settings.SettingsViewModel
import com.sdk.dicegame.ui.theme.DiceGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val isSoundOn = viewModel.state.collectAsState().value.sound
                DiceGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navHostController = rememberNavController()
                    NavHost(navController = navHostController, startDestination = Screen.SplashScreen.route) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navHostController)
                        }
                        composable(route = Screen.StartScreen.route) {
                            IntroScreen(navHostController = navHostController)
                        }
                        composable(route = Screen.SettingsScreen.route) {
                            SettingScreen(navHostController = navHostController)
                        }
                        composable(route = Screen.ComputerScreen.route) {
                            ComputerScreen(navHostController = navHostController) {
                                if (isSoundOn) {
                                    startSound()
                                }
                            }
                        }
                        composable(route = Screen.GameScreen.route) {
                            GameScreen(navHostController = navHostController) {
                                if (isSoundOn) {
                                    startSound()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private fun startSound() {
        val sound = listOf(R.raw.dice_m1, R.raw.dice_m2, R.raw.dice_m3).random()
        val mediaPlayer = MediaPlayer.create(this, sound)
        mediaPlayer.start()
    }
}
