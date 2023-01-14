package com.sdk.dicegame.presentation.intro_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.sdk.dicegame.R
import com.sdk.dicegame.presentation.screen.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    Image(
        painter = painterResource(id = R.drawable.img_1),
        contentDescription = "Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate(Screen.StartScreen.route)
    }
}