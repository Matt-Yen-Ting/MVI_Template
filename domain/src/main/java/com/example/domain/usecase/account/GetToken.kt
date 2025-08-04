package com.example.domain.usecase.account

import com.example.data.datastore.PreferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetToken @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {

    suspend operator fun invoke(): String {
        return preferencesDataStore.getToken().first()
    }
}