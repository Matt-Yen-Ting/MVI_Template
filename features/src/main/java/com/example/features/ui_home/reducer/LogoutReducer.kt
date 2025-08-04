package com.example.features.ui_home.reducer

import com.example.data.common_data.Reducer
import com.example.features.ui_home.HomeIntent
import com.example.features.ui_home.state.LogoutUiState
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