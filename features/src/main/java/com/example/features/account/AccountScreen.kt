package com.example.features.account

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
import com.example.core.design_system.common_view.BackPreviousIcon
import com.example.core.design_system.common_view.MainTopBar
import com.example.features.ui_home.HomeBottomBar

@Composable
fun AccountScreen(navHostController: NavHostController) {
    AccountScreenContent(navHostController)
}

@Composable
fun AccountScreenContent(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            MainTopBar(
                navigationIcon = {
                    BackPreviousIcon {
                        navHostController.popBackStack()
                    }
                },
                title = {
                    Text(stringResource(R.string.account_page))
                }
            )
        },
        bottomBar = {
            HomeBottomBar(navHostController, true)
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.Black)
                .fillMaxSize()
        ) { }

    }
}