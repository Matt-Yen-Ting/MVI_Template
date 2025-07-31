package com.example.mvi_architecture.entities

import com.example.mvi_architecture.data.AnnouncementListData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnouncementResponseDto(
    @Json(name = "data") val data: List<DataDto>
) {
    @JsonClass(generateAdapter = true)
    data class DataDto(
        @Json(name = "id") val id: String,
        @Json(name = "title") val title: String,
        @Json(name = "description") val description: String,
        @Json(name = "posted") val posted: String,
        @Json(name = "modified") val modified: String,
        @Json(name = "url") val url: String,
    )
}

fun AnnouncementResponseDto.toAnnouncementListData(): List<AnnouncementListData> {
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
