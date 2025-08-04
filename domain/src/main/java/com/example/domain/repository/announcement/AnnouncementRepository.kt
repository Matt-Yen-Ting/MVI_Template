package com.example.domain.repository.announcement

import com.example.domain.model.entites.AnnouncementResponseDto

interface AnnouncementRepository {

    suspend fun getAnnouncementList(): AnnouncementResponseDto
}
