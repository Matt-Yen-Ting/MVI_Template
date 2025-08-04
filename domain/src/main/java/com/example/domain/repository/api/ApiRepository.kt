package com.example.domain.repository.api

import com.example.core.di.IoDispatcher
import com.example.domain.model.entites.AnnouncementResponseDto
import com.example.domain.model.entites.LoginResponseDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val testApi: TestApi,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

    suspend fun login(account: String): LoginResponseDto =
        withContext(ioDispatcher) {
            testApi.login("zh-tw", 1)
        }

    suspend fun logout() = withContext(ioDispatcher) {
        testApi.logout("zh-tw", 1)
    }

    suspend fun getAnnouncementList(): AnnouncementResponseDto =
        withContext(ioDispatcher) {
            testApi.getAnnouncementList("zh-tw", 1)
        }

}