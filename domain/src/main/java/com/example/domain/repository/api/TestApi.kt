package com.example.domain.repository.api

import com.example.domain.model.entites.AnnouncementResponseDto
import com.example.domain.model.entites.LoginResponseDto
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