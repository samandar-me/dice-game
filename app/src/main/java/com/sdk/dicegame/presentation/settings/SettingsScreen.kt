package com.sdk.dicegame.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sdk.dicegame.component.BlurImage
import com.sdk.dicegame.component.SettingsIcon
import com.sdk.dicegame.R

@Composable
fun SettingScreen(
    navHostController: NavHostController,
) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    BlurImage()

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SettingsIcon(
            isChecked = isChecked,
            firstIcon = R.drawable.baseline_looks_one_24,
            secondIcon = R.drawable.baseline_looks_two_24,
            description = "First start",
            onClick = {
                isChecked = !isChecked
            }
        )
        SettingsIcon(
            isChecked = false,
            firstIcon = R.drawable.friendly,
            secondIcon = R.drawable.bot,
            description = "Play with",
            onClick = {

            }
        )
        SettingsIcon(
            isChecked = false,
            firstIcon = R.drawable.baseline_volume_up_24,
            secondIcon = R.drawable.baseline_volume_off_24,
            description = "Sound",
            onClick = {

            }
        )
        SettingsIcon(
            isChecked = false,
            firstIcon = R.drawable.baseline_looks_one_24,
            secondIcon = R.drawable.baseline_looks_two_24,
            description = "When finish",
            onClick = {

            }
        )
    }
    IconButton(onClick = { navHostController.popBackStack() }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.padding(top = 39.dp, start = 12.dp)
        )
    }
}