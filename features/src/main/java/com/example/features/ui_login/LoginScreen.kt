package com.example.features.ui_login

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core.design_system.MainTopBar
import com.example.data.common_data.navigation.Screen
import com.example.features.ui_login.state.LoginUiState

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle(LoginUiState())
    LoginScreenContent(loginUiState) { intent ->
        viewModel.sendIntent(intent)
    }
    HandleLoginUiState(navHostController, loginUiState) { intent ->
        viewModel.sendIntent(intent)
    }
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    sendIntent: (intent: LoginIntent) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = {
                    Text("Login Page")
                }
            )
        }

    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            BackHandler {

            }
            val accountText = remember {
                mutableStateOf("")
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        modifier = Modifier,
                        value = accountText.value,
                        onValueChange = { accountText.value = it }
                    )
                    Button(
                        enabled = accountText.value.isNotEmpty(),
                        onClick = {
                            sendIntent(LoginIntent.Login(""))
                        }
                    ) {
                        Text("Login in")
                    }
                }
                if (uiState.showLoading) {
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
private fun HandleLoginUiState(
    navHostController: NavHostController,
    uiState: LoginUiState,
    sendIntent: (LoginIntent) -> Unit
) {
    val context = LocalContext.current

    when {
        uiState.loginSuccess -> {
            Toast.makeText(context, "登入成功!!", Toast.LENGTH_SHORT).show()
            sendIntent(LoginIntent.ResetState())
            LaunchedEffect(Unit) {
                navHostController.navigate(Screen.HomeScreen.route)
            }
        }

        uiState.loginFail -> {
            Toast.makeText(context, "登入失敗!!", Toast.LENGTH_SHORT).show()

        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(LoginUiState()) {}
}