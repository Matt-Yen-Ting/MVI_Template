package com.example.mvi_architecture.repositories

import com.example.mvi_architecture.entities.AnnouncementResponseDto
import com.example.mvi_architecture.entities.LoginResponseDto

interface ApiRepository {

    suspend fun login(account: String): LoginResponseDto

    suspend fun logout()

    suspend fun getAnnouncementList(): AnnouncementResponseDto
}