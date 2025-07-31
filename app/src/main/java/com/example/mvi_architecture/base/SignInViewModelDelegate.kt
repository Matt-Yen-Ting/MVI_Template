package com.example.mvi_architecture.base

import com.example.mvi_architecture.data.datastore.PreferencesDataStore
import com.example.mvi_architecture.di.ApplicationScope
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
    @ApplicationScope val applicationScope: CoroutineScope,

    ) : SignInViewModelDelegate {
    override val isUserSignIn: StateFlow<String> =
        preferencesDataStore.getToken().stateIn(applicationScope, SharingStarted.Eagerly, "")
    override val isUserSignInValue: String
        get() = isUserSignIn.value

}