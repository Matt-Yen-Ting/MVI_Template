package com.example.mvi_architecture.api

import com.example.mvi_architecture.entities.AnnouncementResponseDto
import com.example.mvi_architecture.entities.LoginResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TestApi {

    @Headers("accept: application/json")
    @GET("{lang}/Events/News")
    suspend fun login(
        @Path("lang") language: String,
        @Query("page") page: Int
    ): LoginResponseDto


    @Headers("accept: application/json")
    @GET("{lang}/Events/News")
    suspend fun logout(
        @Path("lang") language: String,
        @Query("page") page: Int
    )

    @Headers("accept: application/json")
    @GET("{lang}/Events/News")
    suspend fun getAnnouncementList(
        @Path("lang") language: String,
        @Query("page") page: Int
    ): AnnouncementResponseDto
}