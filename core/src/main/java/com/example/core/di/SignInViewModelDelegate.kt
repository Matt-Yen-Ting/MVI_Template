package com.example.core.di

import com.example.data.datastore.PreferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface SignInViewModelDelegate {
    val isUserSignIn: StateFlow<String>
    val isUserSignInValue: String
}

internal class SignInViewModelDelegateImpl @Inject constructor(
    preferencesDataStore: PreferencesDataStore,
    @ApplicationScope applicationScope: CoroutineScope,

    ) : SignInViewModelDelegate {
    override val isUserSignIn: StateFlow<String> =
        preferencesDataStore
            .getToken()
            .stateIn(applicationScope, SharingStarted.Eagerly, "")

    override val isUserSignInValue: String
        get() = isUserSignIn.value

}