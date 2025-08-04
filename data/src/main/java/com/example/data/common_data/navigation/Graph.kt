package com.example.data.common_data.navigation

sealed class Graph(val route: String) {
    data object NavGraph : Graph("nav_host")
}