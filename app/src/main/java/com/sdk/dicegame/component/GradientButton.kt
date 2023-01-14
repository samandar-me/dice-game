package com.sdk.dicegame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.dicegame.ui.theme.EndColor
import com.sdk.dicegame.ui.theme.IndieFont
import com.sdk.dicegame.ui.theme.StartColor

@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(180.dp, height = 70.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.horizontalGradient(listOf(StartColor, EndColor))
            )
            .then(modifier)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontFamily = IndieFont, fontSize = 24.sp, color = Color.White)
    }
}