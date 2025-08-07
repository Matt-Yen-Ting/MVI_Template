package com.example.features.home.reducer

import com.example.data.commondata.Reducer
import com.example.features.home.HomeIntent
import com.example.features.home.state.LogoutUiState
import javax.inject.Inject

class LogoutReducer @Inject constructor() : Reducer<LogoutUiState, HomeIntent> {

    override fun reduce(oldState: LogoutUiState, intent: HomeIntent): LogoutUiState {
        return when (intent) {
            is HomeIntent.Logout -> {
                oldState.copy(
                    showLoading = intent.showLoading,
                    logoutSuccess = intent.logoutSuccess,
                    logoutFail = intent.logoutFail
                )
            }
        }
    }
}