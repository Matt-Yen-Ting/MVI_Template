package com.example.mvi_architecture.ui_home.reducer

import com.example.mvi_architecture.base.Reducer
import com.example.mvi_architecture.ui_home.HomeIntent
import com.example.mvi_architecture.ui_home.state.LogoutUiState
import javax.inject.Inject

class LogoutReducer @Inject constructor() : Reducer<LogoutUiState, HomeIntent> {

    override fun reduce(oldState: LogoutUiState, intent: HomeIntent): LogoutUiState {
      return  when(intent) {
           is HomeIntent.Logout -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    logoutStatus = intent.logoutStatus

                )
            }
        }
    }
}