package com.example.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.commondata.navigation.HomeBottomBarData
import com.example.data.commondata.navigation.Screen
import com.example.core.R

@Composable
fun HomeBottomBar(
    navHostController: NavHostController,
    isHomeChildRoute: Boolean = false,
) {
    val screens = listOf(
        HomeBottomBarData(
            Screen.HomeScreen.route,
            R.drawable.ic_gray_main,
            R.drawable.ic_green_main,
            stringResource(R.string.overview)

        ),
        HomeBottomBarData(
            Screen.AccountScreen.route,
            R.drawable.ic_gray_account,
            R.drawable.ic_green_account,
            stringResource(R.string.account)

        ),
        HomeBottomBarData(
            Screen.MoreScreen.route,
            R.drawable.ic_gray_more,
            R.drawable.ic_green_more,
            stringResource(R.string.more)
        )
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column {
        BottomNavigation(
            modifier = Modifier.padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            screens.forEach { bottomBarData ->
                AddItem(
                    isHomeChildRoute,
                    navHostController,
                    currentDestination,
                    bottomBarData
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    isHomeChildRoute: Boolean = false,
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    homeBottomBarData: HomeBottomBarData
) {
    val isSelected = remember {
        mutableStateOf(false)
    }
    BottomNavigationItem(
        label = {
            Text(homeBottomBarData.title, color =
                when {
                    isHomeChildRoute && homeBottomBarData.route == currentDestination?.route -> Color.Green
                    else -> Color.Black
                }
            )
        },
        icon = {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(
                    when {
                        isHomeChildRoute && homeBottomBarData.route == currentDestination?.route -> homeBottomBarData.selectedIconId
                        else -> homeBottomBarData.notSelectedIconId
                    }
                ),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            isSelected.value = it.route == homeBottomBarData.route
            return@any it.route == homeBottomBarData.route
        } == true,
        selectedContentColor = Color.Green,
        unselectedContentColor = Color.Black,
        onClick = {
            navHostController.navigate(homeBottomBarData.route)
        }
    )
}

@Preview
@Composable
fun HomeBottomBarPreview() {
    HomeBottomBar(rememberNavController())
}