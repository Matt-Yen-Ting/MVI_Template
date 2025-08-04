package com.example.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.MainTopBarTheme

@Composable
fun MainTopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit = {},
    action: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = title,
        modifier = Modifier.background(Color.Magenta),
        backgroundColor = Color.Unspecified,
        navigationIcon = navigationIcon,
        actions = action,
        elevation = 0.dp
    )

}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(
        navigationIcon = {
            BackPreviousIcon { }
        },
        title = {
            Text("Test")
        }
    )
}