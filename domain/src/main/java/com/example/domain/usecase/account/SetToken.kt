package com.example.domain.usecase.account

import com.example.data.datastore.PreferencesDataStore
import javax.inject.Inject

class SetToken @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {

    suspend operator fun invoke(token: String) {
        preferencesDataStore.setToken(token)
    }
}