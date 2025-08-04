package com.example.domain.model.data

import com.example.domain.model.entites.AnnouncementResponseDto

data class AnnouncementListData(
    val id: String,
    val title: String,
    val description: String,
    val posted: String,
    val modified: String,
    val url: String,
)

fun AnnouncementResponseDto.toAnnouncementDataList(): List<AnnouncementListData> {
    return this.data.map {
        AnnouncementListData(
            it.id,
            it.title,
            it.description,
            it.posted,
            it.modified,
            it.url
        )
    }

}