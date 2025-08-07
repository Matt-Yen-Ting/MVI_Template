package com.example.data.commondata.navigation

sealed class Graph(val route: String) {
    data object NavGraph : Graph("nav_host")
}