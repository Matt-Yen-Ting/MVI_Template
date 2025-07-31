package com.example.mvi_architecture.ui_home

import android.app.Dialog
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.mvi_architecture.ui_home.state.LogoutUiState
import com.example.mvi_architecture.ui_view.BackPreviousIcon

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            MainTopBar(
                navigationIcon = {
                    BackPreviousIcon {
                        showDialog.value = true
                    }
                },
                title = {
                    Text("Home Page")
                }
            )
        }

    ) { contentPadding ->
        val context = LocalContext.current
        val logoutUiState by viewModel.logoutUiState.collectAsState(LogoutUiState())
        val showApiErrorDialog = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.White)
        ) {
            BackHandler {
                showDialog.value = true

            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    navHostController.navigate(Screen.AnnouncementScreen.route)
                }) {
                    Text("Check Announcement")
                }

                if (logoutUiState.showLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = Color.Red
                    )
                }
            }
            LogoutDialog(viewModel, showDialog)
            ApiErrorDialog(showApiErrorDialog)
            when (logoutUiState.logoutStatus) {
                ResponseStatus.SUCCESS -> {
                    Toast.makeText(context, "登出成功!!", Toast.LENGTH_SHORT).show()

                    LaunchedEffect(Unit) {
                        navHostController.popBackStack()
                        navHostController.navigate(Screen.LoginScreen.route)
                    }
                }

                ResponseStatus.FAIL -> {
                    showApiErrorDialog.value = true
                }

                ResponseStatus.INIT -> {

                }
            }
        }

    }
}

@Composable
fun LogoutDialog(
    viewModel: HomeViewModel,
    showDialog: MutableState<Boolean>
) {
    if (showDialog.value) {
        AlertDialog(
            {
                showDialog.value = false
            },
            text = {
                Text("是否要登出?")
            },
            confirmButton = {
                Text("確定", modifier = Modifier.clickable {
                    viewModel.handleIntent(HomeIntent.Logout())
                    showDialog.value = false
                })
            },
            dismissButton = {
                Text("取消", modifier = Modifier.clickable {
                    showDialog.value = false

                })
            }
        )
    }
}

@Composable
fun ApiErrorDialog(showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        AlertDialog(
            {
                showDialog.value = false
            },
            text = {
                Text("登出錯誤!!")
            },
            confirmButton = {
                Text("確定", modifier = Modifier.clickable {
                    showDialog.value = false
                })
            },
            dismissButton = {

            }
        )
    }
}
