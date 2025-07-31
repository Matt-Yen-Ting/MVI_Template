package com.example.mvi_architecture.ui_announcement.state

import com.example.mvi_architecture.data.AnnouncementListData
import com.example.mvi_architecture.data.ResponseStatus


data class DataListUiState(
    val showLoading: Boolean = false,
    val getDataStatus: ResponseStatus = ResponseStatus.INIT,
    val dataList: List<AnnouncementListData> = emptyList()
)


