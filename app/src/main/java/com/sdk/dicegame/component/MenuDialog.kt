package com.sdk.dicegame.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sdk.dicegame.MainActivity

@Composable
fun MenuDialog(
    modifier: Modifier = Modifier,
    isDialogOpen: Boolean,
    onQuitClicked:() -> Unit,
    onResumeClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isDialogOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            GradientButton(text = "Resume") {
                onResumeClick()
            }
            GradientButton(text = "Quit Game") {
                onQuitClicked()
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}