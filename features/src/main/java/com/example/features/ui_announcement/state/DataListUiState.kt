package com.example.features.ui_announcement.state

import com.example.domain.model.data.AnnouncementListData


data class DataListUiState(
    val showLoading: Boolean = false,
    val getDataSuccess: Boolean = false,
    val getDataFail: Boolean = false,
    val dataList: List<AnnouncementListData> = emptyList()
)


