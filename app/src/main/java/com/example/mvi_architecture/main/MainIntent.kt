package com.example.mvi_architecture.main

sealed class MainIntent {
    data object GetToken : MainIntent()

}