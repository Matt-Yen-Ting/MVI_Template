@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.core.designsystem.commonview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainTopBar(
    navigationIcon: @Composable () -> Unit = { },
    title: @Composable () -> Unit = {},
    action: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = title,
        modifier = Modifier.background(Color.Magenta),
        navigationIcon = navigationIcon,
        actions = action,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Cyan
        )
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