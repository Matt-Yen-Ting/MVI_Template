package com.example.mvi_architecture.repositories

import com.example.mvi_architecture.api.TestApi
import com.example.mvi_architecture.di.IoDispatcher
import com.example.mvi_architecture.entities.AnnouncementResponseDto
import com.example.mvi_architecture.entities.LoginResponseDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val testApi: TestApi,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : ApiRepository {

    override suspend fun login(account: String): LoginResponseDto =
        withContext(ioDispatcher) {
           testApi.login("zh-tw", 1)
        }

    override suspend fun logout() = withContext(ioDispatcher) {
        testApi.logout("zh-tw", 1)
    }

    override suspend fun getAnnouncementList(): AnnouncementResponseDto = withContext(ioDispatcher) {
        testApi.getAnnouncementList("zh-tw", 1)
    }




}