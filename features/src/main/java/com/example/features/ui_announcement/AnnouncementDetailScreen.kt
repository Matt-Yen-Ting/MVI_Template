package com.example.features.ui_announcement

import android.os.Build
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.design_system.common_view.BackPreviousIcon
import com.example.core.design_system.common_view.MainTopBar
import com.example.core.R

@Composable
fun AnnouncementDetailScreen(
    navHostController: NavHostController,
    title: String,
    viewModel: AnnouncementDetailViewModel = hiltViewModel()
) {
    AnnouncementDetailScreenContent(navHostController, title) { intent ->
    }

}

@Composable
fun AnnouncementDetailScreenContent(
    navHostController: NavHostController,
    title: String,
    sendIntent: (AnnouncementDetailIntent) -> Unit
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
                    Text(stringResource(R.string.announcement_detail_page))
                }
            )
        }

    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) contentPadding.calculateBottomPadding() else 0.dp
                )
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.this_is_the_title),
                    fontSize = 16.sp,
                    color = Color.Red
                )
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

@Preview
@Composable
fun AnnouncementDetailScreenPreview() {
    AnnouncementDetailScreenContent(rememberNavController(), "Test passed title content") { }
}