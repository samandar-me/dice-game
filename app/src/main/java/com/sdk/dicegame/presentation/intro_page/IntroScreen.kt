package com.sdk.dicegame.presentation.intro_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.dicegame.MainActivity
import com.sdk.dicegame.R
import com.sdk.dicegame.component.BlurImage
import com.sdk.dicegame.component.GradientButton
import com.sdk.dicegame.presentation.screen.Screen
import com.sdk.dicegame.presentation.settings.SettingsViewModel

@Composable
fun IntroScreen(
    navHostController: NavHostController,
) {
    val context = LocalContext.current as MainActivity
    val viewModel: SettingsViewModel = hiltViewModel()
    BlurImage()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text = "Start") {
            if (!viewModel.state.value.playWith) {
                navHostController.navigate(Screen.GameScreen.route)
            } else {
                navHostController.navigate(Screen.ComputerScreen.route)
            }
        }
        GradientButton(text = "Settings") {
            navHostController.navigate(Screen.SettingsScreen.route)
        }
        GradientButton(text = "Quit Game") {
            context.finish()
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}