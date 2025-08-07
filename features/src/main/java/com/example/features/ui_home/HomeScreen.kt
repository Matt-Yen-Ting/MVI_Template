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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.design_system.common_view.BackPreviousIcon
import com.example.core.design_system.common_view.MainTopBar
import com.example.data.common_data.navigation.Screen
import com.example.features.ui_home.state.LogoutUiState
import com.example.core.R

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
                    Text(stringResource(R.string.home_page))
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
                    Text(stringResource(R.string.check_announcement))
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
            Toast.makeText(context, stringResource(R.string.logout_success), Toast.LENGTH_SHORT).show()
            SideEffect {
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
                Text(stringResource(R.string.do_you_want_to_log_out))
            },
            confirmButton = {
                Text(stringResource(R.string.confirm), modifier = Modifier.clickable {
                    sendIntent(HomeIntent.Logout())
                    showDialog.value = false
                })
            },
            dismissButton = {
                Text(stringResource(R.string.cancel), modifier = Modifier.clickable {
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
                Text(stringResource(R.string.logout_error))
            },
            confirmButton = {
                Text(stringResource(R.string.confirm), modifier = Modifier.clickable {
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
