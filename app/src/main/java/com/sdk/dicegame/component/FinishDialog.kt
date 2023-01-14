package com.sdk.dicegame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.dicegame.ui.theme.EndColor
import com.sdk.dicegame.ui.theme.StartColor
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun FinishDialog(
    modifier: Modifier = Modifier,
    isDialogOpen: Boolean,
    num: Int,
    onOkClicked: () -> Unit,
) {
    val party = Party(
        speed = 0f,
        maxSpeed = 70f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
        emitter = Emitter(duration = 1100, TimeUnit.MILLISECONDS).max(1100),
        position = Position.Relative(0.5, 0.3)
    )
    if (isDialogOpen) {
        KonfettiView(parties = listOf(party), modifier = modifier.fillMaxSize())
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp, 160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Brush.linearGradient(listOf(StartColor, EndColor))),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                    .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Congratulations!\nPlayer $num won!",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    TextButton(onClick = { onOkClicked() }) {
                        Text(
                            text = "Ok",
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}