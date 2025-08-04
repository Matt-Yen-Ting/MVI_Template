package com.example.domain.model.data

import com.example.domain.model.entites.LoginResponseDto

data class LoginResponseData(
    val testString: Int
)

fun LoginResponseDto.toLoginResponseData(): LoginResponseData {
    val dto = this
    return LoginResponseData(dto.total)
}

