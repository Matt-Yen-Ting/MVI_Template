package com.example.data.commondata.navigation

import kotlinx.serialization.Serializable


sealed class Screen {

    @Serializable
    data object Splash : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Announcement : Screen()

    @Serializable
    data class AnnouncementDetail(val title: String) : Screen()

    @Serializable
    data object Account : Screen()

    @Serializable
    data object More : Screen()

}