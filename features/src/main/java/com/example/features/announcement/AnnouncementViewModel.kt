package com.example.features.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.announcement.reducer.AnnouncementReducer
import com.example.domain.usecase.announcement.AnnouncementUseCases
import com.example.features.announcement.state.AnnouncementState
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
    private val announcementReducer: AnnouncementReducer
) : ViewModel() {

    private val _announcementState = MutableStateFlow(AnnouncementState())
    val announcementState: StateFlow<AnnouncementState> = _announcementState


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
            _announcementState.update {
                announcementReducer.reduce(
                    _announcementState.value,
                    AnnouncementIntent.GetDataList(
                        false,
                        getDataSuccess = false,
                        getDataFail = true
                    )
                )
            }
        }
        ) {
            _announcementState.update {
                announcementReducer.reduce(
                    _announcementState.value,
                    AnnouncementIntent.GetDataList(true)
                )
            }
            val announcementListData = announcementUseCases.getAnnouncementList()
            _announcementState.update {
                announcementReducer.reduce(
                    _announcementState.value,
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