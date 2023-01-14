package com.sdk.dicegame.presentation.intro_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sdk.dicegame.MainActivity
import com.sdk.dicegame.R
import com.sdk.dicegame.component.GradientButton
import com.sdk.dicegame.presentation.screen.Screen

@Composable
fun IntroScreen(
    navHostController: NavHostController,
) {
    val context = LocalContext.current as MainActivity
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp),
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text = "Start") {
            navHostController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.StartScreen.route) {
                    inclusive = true
                }
            }
        }
        GradientButton(text = "Settings") {

        }
        GradientButton(text = "Quit Game") {
            context.finish()
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}