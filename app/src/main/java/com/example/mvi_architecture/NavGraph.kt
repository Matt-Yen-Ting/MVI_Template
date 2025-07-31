package com.example.mvi_architecture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mvi_architecture.data.navigation.Screen
import com.example.mvi_architecture.main.SplashScreen
import com.example.mvi_architecture.main.state.SplashViewModel
import com.example.mvi_architecture.ui_announcement.AnnouncementDetailScreen
import com.example.mvi_architecture.ui_announcement.AnnouncementScreen
import com.example.mvi_architecture.ui_home.HomeScreen
import com.example.mvi_architecture.ui_login.LoginScreen

@Composable
fun NavGraph(navHostController: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {
    NavHost(
        navController = navHostController,
        startDestination = if (viewModel.signInViewModelDelegate.isUserSignInValue.isNotEmpty()) Screen.HomeScreen.route else Screen.LoginScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navHostController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navHostController)
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(navHostController)
        }

        composable(Screen.AnnouncementScreen.route) {
            AnnouncementScreen(navHostController)
        }

        composable(
            Screen.AnnouncementDetailScreen.route + "?title={title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { stackEntry ->
            AnnouncementDetailScreen(
                navHostController,
                stackEntry.arguments?.getString("title") ?: ""
            )
        }

    }
}