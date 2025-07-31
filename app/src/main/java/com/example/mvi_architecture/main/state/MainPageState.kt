package com.example.mvi_architecture.main.state


sealed class MainPageState {
    object LoginPage : MainPageState()
    object HomePage : MainPageState()

}