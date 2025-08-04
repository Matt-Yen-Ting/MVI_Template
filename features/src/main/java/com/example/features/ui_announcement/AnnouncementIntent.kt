package com.example.features.ui_announcement

import com.example.domain.model.data.AnnouncementListData

sealed class AnnouncementIntent {

    data class GetDataList(
        val showLoading: Boolean = false,
        val getDataSuccess: Boolean = false,
        val getDataFail:Boolean = false,
        val dataList: List<AnnouncementListData> = emptyList()
    ) : AnnouncementIntent()

}