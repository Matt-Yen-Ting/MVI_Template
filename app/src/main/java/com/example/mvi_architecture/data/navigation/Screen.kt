package com.example.mvi_architecture.data.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash")
    data object LoginScreen : Screen("login")
    data object HomeScreen : Screen("home")
    data object AnnouncementScreen : Screen("announcement")
    data object AnnouncementDetailScreen : Screen("announcement-detail")
}