package com.example.features.announcement.reducer

import com.example.data.commondata.Reducer
import com.example.features.announcement.AnnouncementIntent
import com.example.features.announcement.state.DataListUiState
import javax.inject.Inject

class GetDataListReducer @Inject constructor() : Reducer<DataListUiState, AnnouncementIntent> {

    override fun reduce(oldState: DataListUiState, intent: AnnouncementIntent): DataListUiState {
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