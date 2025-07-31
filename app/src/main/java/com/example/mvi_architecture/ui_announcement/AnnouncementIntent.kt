package com.example.mvi_architecture.ui_announcement

import com.example.mvi_architecture.data.AnnouncementListData
import com.example.mvi_architecture.data.ResponseStatus

sealed class AnnouncementIntent {

    data class GetDataList(
        val showLoading: Boolean = false,
        val getDataListStatus: ResponseStatus = ResponseStatus.INIT,
        val dataList: List<AnnouncementListData> = emptyList()
    ) : AnnouncementIntent()

}