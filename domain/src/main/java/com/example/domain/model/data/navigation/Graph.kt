package com.example.domain.model.data.navigation

sealed class Graph(val route: String) {
    data object NavGraph : Graph("nav_host")
}