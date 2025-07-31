package com.example.mvi_architecture.ui_announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mvi_architecture.base.common.MainTopBar
import com.example.mvi_architecture.ui_view.BackPreviousIcon

@Composable
fun AnnouncementDetailScreen(
    navHostController: NavHostController,
    title: String
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
                    Text("Announcement Detail Page")
                }
            )
        }

    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("這是傳過來Title:", fontSize = 16.sp, color = Color.Red)
                Text(
                    title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}