package com.example.mvi_architecture.data.navigation

sealed class Graph(val route: String) {
    data object NavGraph : Graph("nav_host")
    data object LoginGraph:Graph("login_graph")

}