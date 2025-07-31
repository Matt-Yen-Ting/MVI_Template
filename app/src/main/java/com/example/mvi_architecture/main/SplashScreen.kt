package com.example.mvi_architecture.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvi_architecture.R
import com.example.mvi_architecture.data.navigation.Screen
import com.example.mvi_architecture.main.state.MainPageState
import com.example.mvi_architecture.main.state.SplashViewModel

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Box(
        Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        val mainPageState by viewModel.mainPageUiState.collectAsState(MainPageState.LoginPage)
        if (mainPageState is MainPageState.LoginPage) {
            LaunchedEffect(Unit) {
                navHostController.navigate(Screen.LoginScreen.route)
            }
        } else {
            LaunchedEffect(Unit) {
                navHostController.navigate(Screen.HomeScreen.route)
            }
        }
    }
}