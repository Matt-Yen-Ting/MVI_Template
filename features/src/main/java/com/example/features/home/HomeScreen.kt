package com.example.features.home

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
import com.example.core.designsystem.commonview.BackPreviousIcon
import com.example.core.designsystem.commonview.MainTopBar
import com.example.data.commondata.navigation.Screen
import com.example.core.R
import com.example.features.home.state.HomeState

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState by viewModel.homeState.collectAsStateWithLifecycle(HomeState())

    val showLogoutDialog = remember {
        mutableStateOf(false)
    }

    val showApiErrorDialog = remember {
        mutableStateOf(false)
    }

    HomeScreenContent(
        navHostController,
        showLogoutDialog,
        homeState
    )
    LogoutDialog(showLogoutDialog) { intent ->
        viewModel.sendIntent(intent)
    }
    ApiErrorDialog(showApiErrorDialog)
    HandleLogoutUiState(navHostController, showApiErrorDialog, homeState)


}

@Composable
fun HomeScreenContent(
    navHostController: NavHostController,
    showLogoutDialog: MutableState<Boolean>,
    homeState: HomeState
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
                    navHostController.navigate(Screen.Announcement)
                }) {
                    Text(stringResource(R.string.check_announcement))
                }

                if (homeState.showLoading) {
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
    homeState: HomeState
) {
    val context = LocalContext.current
    when {
        homeState.logoutSuccess -> {
            Toast.makeText(context, stringResource(R.string.logout_success), Toast.LENGTH_SHORT)
                .show()
            navHostController.popBackStack()
            navHostController.navigate(Screen.Login)

        }

        homeState.logoutFail -> {
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
    HomeScreenContent(rememberNavController(), showLogoutDialog, HomeState())
}
