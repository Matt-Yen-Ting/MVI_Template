package com.example.core.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.R

@Composable
fun BackPreviousIcon(
    tint: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Companion.Black,
    onClick: () -> Unit = {}
) {
    androidx.compose.material.Icon(
        painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_black_left_arrow),
        contentDescription = "It is back arrow",
        tint = tint,
        modifier = androidx.compose.ui.Modifier.Companion.clickable { onClick.invoke() }
            .padding(start = 16.dp)
    )
}

@Preview
@Composable
fun BackPreviousIconPreview() {
    BackPreviousIcon {  }
}