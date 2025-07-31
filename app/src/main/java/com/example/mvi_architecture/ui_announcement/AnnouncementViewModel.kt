package com.example.mvi_architecture.ui_announcement

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.entities.toAnnouncementListData
import com.example.mvi_architecture.repositories.ApiRepository
import com.example.mvi_architecture.ui_announcement.reducer.GetDataListReducer
import com.example.mvi_architecture.ui_announcement.state.DataListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val getDataListReducer: GetDataListReducer
) : ViewModel() {
    private val _uiIntentChannel = Channel<AnnouncementIntent>()
    val uiIntentChannel = _uiIntentChannel.receiveAsFlow()

    private val _dataListUiState = MutableStateFlow(DataListUiState())
    val dataListUiState: StateFlow<DataListUiState> = _dataListUiState


    init {
        handleIntent(AnnouncementIntent.GetDataList())
    }


    fun sendIntent(intent: AnnouncementIntent) {
        viewModelScope.launch {
            _uiIntentChannel.send(intent)
        }
    }

    private fun handleIntent(intent: AnnouncementIntent) {
                when (intent) {
                    is AnnouncementIntent.GetDataList -> {
                        getDataList()
                    }
                }


    }

    private fun getDataList() {
        viewModelScope.launch(CoroutineExceptionHandler { context, throwable ->
            _dataListUiState.value = getDataListReducer.reduce(
                _dataListUiState.value,
                AnnouncementIntent.GetDataList(false, getDataListStatus = ResponseStatus.FAIL)
            )
        }
        ) {
            _dataListUiState.value = getDataListReducer.reduce(
                _dataListUiState.value,
                AnnouncementIntent.GetDataList(true)
            )
            val responseDto = apiRepository.getAnnouncementList()
            _dataListUiState.value = getDataListReducer.reduce(
                _dataListUiState.value,
                AnnouncementIntent.GetDataList(
                    false,
                    getDataListStatus = ResponseStatus.SUCCESS,
                    responseDto.toAnnouncementListData()
                )
            )
        }
    }


}