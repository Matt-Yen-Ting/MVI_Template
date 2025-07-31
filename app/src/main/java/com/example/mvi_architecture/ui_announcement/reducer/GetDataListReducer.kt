package com.example.mvi_architecture.ui_announcement.reducer

import com.example.mvi_architecture.base.Reducer
import com.example.mvi_architecture.ui_announcement.AnnouncementIntent
import com.example.mvi_architecture.ui_announcement.state.DataListUiState
import javax.inject.Inject

class GetDataListReducer @Inject constructor() : Reducer<DataListUiState, AnnouncementIntent> {

    override fun reduce(oldState: DataListUiState, intent: AnnouncementIntent): DataListUiState {
        return when (intent) {
            is AnnouncementIntent.GetDataList -> {
                oldState.copy(
                    intent.showLoading,
                    getDataStatus = intent.getDataListStatus,
                    dataList = intent.dataList
                )
            }
        }
    }
}