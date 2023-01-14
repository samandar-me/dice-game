package com.sdk.dicegame.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DiceImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = modifier.size(100.dp)
    )
}