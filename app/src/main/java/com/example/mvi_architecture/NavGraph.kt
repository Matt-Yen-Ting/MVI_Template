package com.example.mvi_architecture

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.core.designsystem.commonview.MainBottomBar
import com.example.data.commondata.navigation.Screen
import com.example.features.account.AccountScreen
import com.example.features.more.MoreScreen
import com.example.mvi_architecture.main.MainViewModel
import com.example.features.announcementdetail.AnnouncementDetailScreen
import com.example.features.announcement.AnnouncementScreen
import com.example.features.home.HomeScreen
import com.example.features.login.LoginScreen

@Composable
fun NavGraph(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {

    val currentDestination by navHostController.currentBackStackEntryAsState()
    val shouldShowBottomBar = remember(currentDestination) {
        currentDestination?.destination?.hierarchy?.any {
            var hasRoute = false
            listOf(Screen.Home, Screen.Account, Screen.More).forEach { screen ->
                hasRoute = it.hasRoute(screen::class) == true
            }
            hasRoute
        } == true
    }
    Scaffold(bottomBar = {
        if (shouldShowBottomBar) {
            MainBottomBar(navHostController)
        }
    }) { innerPadding ->


        NavHost(
            modifier = Modifier.padding(innerPadding).consumeWindowInsets(innerPadding),
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
}