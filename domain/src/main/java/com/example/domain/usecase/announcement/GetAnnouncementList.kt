package com.example.domain.usecase.announcement

import com.example.domain.model.data.AnnouncementListData
import com.example.domain.model.data.toAnnouncementDataList
import com.example.domain.repository.announcement.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementList @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    suspend operator fun invoke(): List<AnnouncementListData> {
        return announcementRepository.getAnnouncementList().toAnnouncementDataList()
    }
}