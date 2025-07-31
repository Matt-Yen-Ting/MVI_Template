package com.example.mvi_architecture.ui_announcement

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvi_architecture.base.common.MainTopBar
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.data.navigation.Screen
import com.example.mvi_architecture.ui_announcement.state.DataListUiState
import com.example.mvi_architecture.ui_view.BackPreviousIcon

@Composable
fun AnnouncementScreen(
    navHostController: NavHostController,
    viewModel: AnnouncementViewModel = hiltViewModel()
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
                    Text("Announcement Page")
                }
            )
        }

    ) { contentPadding ->
        BackHandler {
            navHostController.popBackStack()
        }
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.White)
        ) {
            val dataListUiState by viewModel.dataListUiState.collectAsState(DataListUiState())
            val context = LocalContext.current
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (dataListUiState.showLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = Color.Red
                    )
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    when (dataListUiState.getDataStatus) {
                        ResponseStatus.SUCCESS -> {
                            LazyColumn {
                                dataListUiState.dataList.forEach {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .border(
                                                    BorderStroke(2.dp, Color.Black)
                                                )
                                                .clickable {
                                                    navHostController.navigate(Screen.AnnouncementDetailScreen.route + "?title=${it.title}")
                                                }
                                        ) {
                                            Column {
                                                Text(it.posted, modifier = Modifier.padding(3.dp))
                                                Text(it.title)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        ResponseStatus.FAIL -> {
                            Toast.makeText(context, "取得列表資料失敗!!", Toast.LENGTH_SHORT).show()

                        }

                        ResponseStatus.INIT -> {

                        }
                    }

                }

            }

        }
    }
}


