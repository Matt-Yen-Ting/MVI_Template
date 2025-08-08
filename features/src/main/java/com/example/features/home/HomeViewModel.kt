package com.example.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.account.AccountUseCases
import com.example.features.home.reducer.HomeReducer
import com.example.features.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val homeReducer: HomeReducer
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    fun sendIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Logout -> {
                logout()
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(
            CoroutineExceptionHandler { context, throwable ->
                _homeState.update {
                    homeReducer.reduce(
                        _homeState.value,
                        HomeIntent.Logout(false, logoutSuccess = false, logoutFail = true)
                    )
                }
            }
        ) {
            _homeState.update {
                homeReducer.reduce(
                    _homeState.value,
                    HomeIntent.Logout(true)
                )
            }
            accountUseCases.logout()
            accountUseCases.setToken("")
            _homeState.update {
                homeReducer.reduce(
                    _homeState.value,
                    HomeIntent.Logout(false, logoutSuccess = true, logoutFail = false)
                )
            }
        }
    }


}