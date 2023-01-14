package com.sdk.dicegame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    isEnabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp)),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(containerColor = color, disabledContainerColor = Color.LightGray)
    ) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}