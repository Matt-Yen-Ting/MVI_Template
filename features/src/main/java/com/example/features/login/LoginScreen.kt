package com.example.features.login

import android.os.Build
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core.designsystem.commonview.MainTopBar
import com.example.data.commondata.navigation.Screen
import com.example.core.R
import com.example.features.login.state.LoginState

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle(LoginState())
    LoginScreenContent(loginState) { intent ->
        viewModel.sendIntent(intent)
    }
    HandleLoginUiState(navHostController, loginState) { intent ->
        viewModel.sendIntent(intent)
    }
}

@Composable
fun LoginScreenContent(
    uiState: LoginState,
    sendIntent: (intent: LoginIntent) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = {
                    Text(stringResource(R.string.login_page))
                }
            )
        }

    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding(),
                bottom = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) contentPadding.calculateBottomPadding() else 0.dp
            )
        ) {
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
                        Text(stringResource(R.string.log_in))
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
    uiState: LoginState,
    sendIntent: (LoginIntent) -> Unit
) {
    val context = LocalContext.current

    when {
        uiState.loginSuccess -> {
            Toast.makeText(context, "登入成功!!", Toast.LENGTH_SHORT).show()
            sendIntent(LoginIntent.ResetState())
            SideEffect {
                navHostController.navigate(Screen.Home)
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
    LoginScreenContent(LoginState()) {}
}