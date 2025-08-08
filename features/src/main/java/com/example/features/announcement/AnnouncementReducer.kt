package com.example.features.announcement

import com.example.data.commondata.Reducer
import javax.inject.Inject

class AnnouncementReducer @Inject constructor() : Reducer<AnnouncementState, AnnouncementIntent> {

    override fun reduce(oldState: AnnouncementState, intent: AnnouncementIntent): AnnouncementState {
        return when (intent) {
            is AnnouncementIntent.GetDataList -> {
                oldState.copy(
                    intent.showLoading,
                    getDataSuccess = intent.getDataSuccess,
                    getDataFail = intent.getDataFail,
                    dataList = intent.dataList
                )
            }
        }
    }
}