package com.sdk.dicegame.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.dicegame.R
import com.sdk.dicegame.component.BlurImage
import com.sdk.dicegame.component.SettingsIcon

@Composable
fun SettingScreen(
    navHostController: NavHostController,
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    BlurImage()

    println("@@@Sc${state.firstStart}")

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SettingsIcon(
            isChecked = state.firstStart,
            firstIcon = R.drawable.baseline_looks_one_24,
            secondIcon = R.drawable.baseline_looks_two_24,
            description = "First start",
            onClick = {
                viewModel.onEvent(SettingsEvent.OnFirstStart(state.finishCount))
            }
        )
        SettingsIcon(
            isChecked = state.playWith,
            firstIcon = R.drawable.friendly,
            secondIcon = R.drawable.bot,
            description = "Play with",
            onClick = {
                viewModel.onEvent(SettingsEvent.OnPlayWith(state.playWith))
            }
        )
        SettingsIcon(
            isChecked = state.sound,
            firstIcon = R.drawable.baseline_volume_up_24,
            secondIcon = R.drawable.baseline_volume_off_24,
            description = "Sound",
            onClick = {
                viewModel.onEvent(SettingsEvent.OnSound(state.sound))
            }
        )
        SettingsIcon(
            isChecked = state.finishCount,
            firstIcon = R.drawable.o_00,
            secondIcon = R.drawable._00,
            description = "Finish count",
            onClick = {
                viewModel.onEvent(SettingsEvent.OnFinishCount(state.finishCount))
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