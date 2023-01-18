package com.sdk.dicegame.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SettingsIcon(
    isChecked: Boolean,
    firstIcon: Int,
    secondIcon: Int,
    description: String,
    onClick: () -> Unit,
) {
    IconToggleButton(checked = isChecked, onCheckedChange = { onClick() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = if (isChecked) secondIcon else firstIcon),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = description, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}