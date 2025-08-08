package com.example.features.home

import com.example.data.commondata.Reducer
import javax.inject.Inject

class HomeReducer @Inject constructor() : Reducer<HomeState, HomeIntent> {

    override fun reduce(oldState: HomeState, intent: HomeIntent): HomeState {
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