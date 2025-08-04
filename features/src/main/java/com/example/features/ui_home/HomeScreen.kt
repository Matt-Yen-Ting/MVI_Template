package com.example.features.ui_home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.design_system.BackPreviousIcon
import com.example.core.design_system.MainTopBar
import com.example.domain.model.data.navigation.Screen
import com.example.features.ui_home.state.LogoutUiState
import com.example.features.ui_login.state.LoginUiState

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val logoutUiState by viewModel.logoutUiState.collectAsStateWithLifecycle(LogoutUiState())

    val showLogoutDialog = remember {
        mutableStateOf(false)
    }

    val showApiErrorDialog = remember {
        mutableStateOf(false)
    }

    HomeScreenContent(
        navHostController,
        showLogoutDialog,
        logoutUiState
    )
    LogoutDialog(showLogoutDialog) { intent ->
        viewModel.sendIntent(intent)
    }
    ApiErrorDialog(showApiErrorDialog)
    HandleLogoutUiState(navHostController, showApiErrorDialog, logoutUiState)


}

@Composable
fun HomeScreenContent(
    navHostController: NavHostController,
    showLogoutDialog: MutableState<Boolean>,
    logoutUiState: LogoutUiState
) {
    Scaffold(
        topBar = {
            MainTopBar(
                navigationIcon = {
                    BackPreviousIcon {
                        showLogoutDialog.value = true
                    }
                },
                title = {
                    Text("Home Page")
                }
            )
        }

    ) { contentPadding ->

        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color.White)
        ) {
            BackHandler {
                showLogoutDialog.value = true
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

        }
    }
}

@Composable
private fun HandleLogoutUiState(
    navHostController: NavHostController,
    showApiErrorDialog: MutableState<Boolean>,
    logoutUiState: LogoutUiState
) {
    val context = LocalContext.current
    when {
        logoutUiState.logoutSuccess -> {
            Toast.makeText(context, "登出成功!!", Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                navHostController.popBackStack()
                navHostController.navigate(Screen.LoginScreen.route)
            }
        }

        logoutUiState.logoutFail -> {
            showApiErrorDialog.value = true
        }
    }
}

@Composable
fun LogoutDialog(
    showDialog: MutableState<Boolean>,
    sendIntent: (HomeIntent) -> Unit
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
                    sendIntent(HomeIntent.Logout())
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

@Preview
@Composable
fun HomeScreenContentPreview() {
    val showLogoutDialog = remember {
        mutableStateOf(false)
    }
    HomeScreenContent(rememberNavController(), showLogoutDialog, LogoutUiState())
}
