package com.example.features.announcement.state

import com.example.domain.model.data.AnnouncementListData

data class AnnouncementState(
    val showLoading: Boolean = false,
    val getDataSuccess: Boolean = false,
    val getDataFail: Boolean = false,
    val dataList: List<AnnouncementListData> = emptyList(),
)