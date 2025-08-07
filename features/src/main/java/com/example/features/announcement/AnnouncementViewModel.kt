package com.example.features.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.announcement.reducer.GetDataListReducer
import com.example.domain.usecase.announcement.AnnouncementUseCases
import com.example.features.announcement.state.DataListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val announcementUseCases: AnnouncementUseCases,
    private val getDataListReducer: GetDataListReducer
) : ViewModel() {

    private val _dataListUiState = MutableStateFlow(DataListUiState())
    val dataListUiState: StateFlow<DataListUiState> = _dataListUiState


    init {
        sendIntent(AnnouncementIntent.GetDataList())
    }


    fun sendIntent(intent: AnnouncementIntent) {
        when (intent) {
            is AnnouncementIntent.GetDataList -> {
                getDataList()
            }
        }
    }

    private fun getDataList() {
        viewModelScope.launch(CoroutineExceptionHandler { context, throwable ->
            _dataListUiState.update {
                getDataListReducer.reduce(
                    _dataListUiState.value,
                    AnnouncementIntent.GetDataList(
                        false,
                        getDataSuccess = false,
                        getDataFail = true
                    )
                )
            }
        }
        ) {
            _dataListUiState.update {
                getDataListReducer.reduce(
                    _dataListUiState.value,
                    AnnouncementIntent.GetDataList(true)
                )
            }
            val announcementListData = announcementUseCases.getAnnouncementList()
            _dataListUiState.update {
                getDataListReducer.reduce(
                    _dataListUiState.value,
                    AnnouncementIntent.GetDataList(
                        false,
                        getDataSuccess = true,
                        getDataFail = false,
                        announcementListData
                    )
                )
            }
        }
    }
}