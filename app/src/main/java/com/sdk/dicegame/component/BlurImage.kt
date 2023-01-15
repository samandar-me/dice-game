package com.sdk.dicegame.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sdk.dicegame.R

@Composable
fun BlurImage() {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp),
    )
}