package com.sdk.dicegame.presentation.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.dicegame.R
import com.sdk.dicegame.component.DiceImage
import com.sdk.dicegame.component.FinishDialog
import com.sdk.dicegame.component.MenuDialog
import com.sdk.dicegame.component.PlayButton

@Composable
fun GameScreen() {
    val viewModel: GameViewModel = hiltViewModel()
    val state = viewModel.gameState.collectAsState().value
    val rotateAnimation = viewModel.rotateAnimation.collectAsState().value
    val isGameFinished = viewModel.isGameFinished.collectAsState().value
    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

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
            Text(text = state.player1Score.toString(), color = Color.White, fontSize = 22.sp)
            IconButton(onClick = {
                isDialogOpen = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(text = state.player2Score.toString(), color = Color.White, fontSize = 22.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayButton(
                text = "Player 1",
                color = Color.Magenta,
                isEnabled = state.isButton1Enabled,
                onClick = {
                    viewModel.onEvent(GameEvent.Player1Clicked)
                }
            )
            DiceImage(image = state.im1, modifier = Modifier.rotate(rotateAnimation))
            Text(text = state.currentScore.toString(), fontSize = 30.sp, color = Color.White)
            DiceImage(image = state.im2, modifier = Modifier.rotate(rotateAnimation))
            PlayButton(
                text = "Player 2",
                color = Color.Blue,
                isEnabled = state.isButton2Enabled,
                onClick = {
                    viewModel.onEvent(GameEvent.Player2Clicked)
                }
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
        num = if (state.player1Score > state.player2Score) 1 else 2
    ) {
        viewModel.onEvent(GameEvent.OkButtonClicked)
    }
    MenuDialog(isDialogOpen = isDialogOpen) {
        isDialogOpen = false
    }
}
