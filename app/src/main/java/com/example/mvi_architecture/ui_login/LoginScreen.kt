package com.example.mvi_architecture.ui_login

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvi_architecture.base.common.MainTopBar
import com.example.mvi_architecture.ui_view.BackPreviousIcon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.data.navigation.Screen
import com.example.mvi_architecture.ui_login.state.LoginUiState

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
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
            val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())
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
                            viewModel.handleIntent(LoginIntent.Login(""))
                        }
                    ) {
                        Text("Login in")
                    }


                }
                if (loginUiState.showLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = Color.Red
                    )
                }
            }

            HandleLoginUiState(navHostController, viewModel)
        }

    }
}

@Composable
fun HandleLoginUiState(
    navHostController: NavHostController,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())
    when (loginUiState.loginStatus) {
        
        ResponseStatus.SUCCESS -> {
            Toast.makeText(context, "登入成功!!", Toast.LENGTH_SHORT).show()
            viewModel.handleIntent(LoginIntent.ResetState())
            LaunchedEffect(Unit) {
                navHostController.navigate(Screen.HomeScreen.route)
            }
        }

        ResponseStatus.FAIL -> {
            Toast.makeText(context, "登入失敗!!", Toast.LENGTH_SHORT).show()
        }

        ResponseStatus.INIT -> {

        }
    }

}

//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}