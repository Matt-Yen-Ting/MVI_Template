package com.example.mvi_architecture

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.data.commondata.navigation.Screen
import com.example.features.account.AccountScreen
import com.example.features.more.MoreScreen
import com.example.mvi_architecture.main.MainViewModel
import com.example.features.announcement.AnnouncementDetailScreen
import com.example.features.announcement.AnnouncementScreen
import com.example.features.home.HomeScreen
import com.example.features.login.LoginScreen

@Composable
fun NavGraph(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    NavHost(
        navController = navHostController,
        startDestination = if (viewModel.signInViewModelDelegate.isUserSignInValue.isNotEmpty()) Screen.Home else Screen.Login
    ) {
        composable<Screen.Login> {
            LoginScreen(navHostController)
        }

        composable<Screen.Home> {
            HomeScreen(navHostController)
        }

        composable<Screen.Announcement> {
            AnnouncementScreen(navHostController)
        }

        composable<Screen.AnnouncementDetail>
        { stackEntry ->
            val announcementDetail: Screen.AnnouncementDetail = stackEntry.toRoute()
            AnnouncementDetailScreen(
                navHostController,
                announcementDetail.title
            )
        }

        composable<Screen.Account> {
            AccountScreen(navHostController)
        }

        composable<Screen.More> {
            MoreScreen(navHostController)
        }
    }
}