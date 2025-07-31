package com.example.mvi_architecture.ui_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mvi_architecture.R

@Composable
fun BackPreviousIcon(
    tint: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_black_left_arrow),
        contentDescription = "It is back arrow",
        tint = tint,
        modifier = Modifier.clickable { onClick.invoke() }.padding(start = 16.dp)
    )
}