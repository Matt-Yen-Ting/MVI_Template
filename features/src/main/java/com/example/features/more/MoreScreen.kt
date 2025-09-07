package com.example.features.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.core.R
import com.example.core.designsystem.commonview.BackPreviousIcon
import com.example.core.designsystem.commonview.MainTopBar

@Composable
fun MoreScreen(navHostController: NavHostController) {
    MoreScreenContent(navHostController)
}

@Composable
fun MoreScreenContent(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            MainTopBar(
                navigationIcon = {
                    BackPreviousIcon {
                        navHostController.popBackStack()
                    }
                },
                title = {
                    Text(stringResource(R.string.more_page))
                }
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.Black)
                .fillMaxSize()
        ) { }

    }
}