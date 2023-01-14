package com.sdk.dicegame.presentation.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sdk.dicegame.R
import com.sdk.dicegame.component.DiceImage
import com.sdk.dicegame.component.PlayButton
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val viewModel: GameViewModel = hiltViewModel()
    val state = viewModel.gameState.collectAsState().value
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .weight(.2f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.player1Score.toString(), color = Color.White, fontSize = 22.sp)
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
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
            DiceImage(image = state.im1, modifier = Modifier.rotate(state.rotation))
            Text(text = state.currentScore.toString(), fontSize = 30.sp, color = Color.White)
            DiceImage(image = state.im2, modifier = Modifier.rotate(state.rotation))
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
}
