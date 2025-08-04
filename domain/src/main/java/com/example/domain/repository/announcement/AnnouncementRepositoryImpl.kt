package com.example.domain.repository.announcement

import com.example.domain.model.entites.AnnouncementResponseDto
import com.example.domain.repository.api.ApiRepository
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository
) : AnnouncementRepository {

    override suspend fun getAnnouncementList(): AnnouncementResponseDto {
        return apiRepository.getAnnouncementList()
    }
}