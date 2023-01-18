package com.sdk.dicegame.presentation.computer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.dicegame.R
import com.sdk.dicegame.component.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ComputerScreen(
    navHostController: NavHostController,
    startSound: () -> Unit
) {
    val viewModel: ComputerViewModel = hiltViewModel()
    val state = viewModel.gameState.collectAsState().value
    val rotateAnimation = viewModel.rotateAnimation.collectAsState().value
    val isGameFinished = viewModel.isGameFinished.collectAsState().value
    var isDialogOpen by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    BackgroundImage()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (isGameFinished || isDialogOpen) 3.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .weight(.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.playerScore.toString(), color = Color.White, fontSize = 22.sp)
            IconButton(onClick = {
                isDialogOpen = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(text = state.computerScore.toString(), color = Color.White, fontSize = 22.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayButton(
                text = "\tYou\t\t",
                color = Color.Magenta,
                isEnabled = state.isButton1Enabled,
                onClick = {
                    coroutineScope.launch {
                        startSound()
                        viewModel.onEvent(ComputerEvent.PlayerClicked)
                        delay(1200L)
                        startSound()
                        viewModel.onEvent(ComputerEvent.ComputerClicked)
                    }
                }
            )
            DiceImage(image = state.im1, modifier = Modifier.rotate(rotateAnimation))
            Text(text = state.currentScore.toString(), fontSize = 30.sp, color = Color.White)
            DiceImage(image = state.im2, modifier = Modifier.rotate(rotateAnimation))
            PlayButton(
                text = "Computer",
                color = Color.Blue,
                isEnabled = state.isButton2Enabled,
                onClick = {}
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.2f)
        )
    }
    FinishDialog(
        isDialogOpen = isGameFinished,
        text = if (state.playerScore > state.computerScore) "You" else "Computer"
    ) {
        viewModel.onEvent(ComputerEvent.OkButtonClicked)
    }
    MenuDialog(
        isDialogOpen = isDialogOpen,
        onResumeClick = {
            isDialogOpen = false
        },
        onQuitClicked = {
            navHostController.popBackStack()
        }
    )
}