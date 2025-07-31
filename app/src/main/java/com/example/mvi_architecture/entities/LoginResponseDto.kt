package com.example.mvi_architecture.entities

import com.example.mvi_architecture.data.LoginResponseData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @Json(name = "total") val total: Int
)

fun LoginResponseDto.toLoginResponseData(): LoginResponseData {
    val dto = this
   return LoginResponseData(dto.total)
}
