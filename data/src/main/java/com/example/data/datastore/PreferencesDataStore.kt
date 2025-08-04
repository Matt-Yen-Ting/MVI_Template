package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


@Reusable
class PreferencesDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun setToken(token: String) {
        context.dataStore.edit { setting ->
            setting[TOKEN] = token
        }
    }

    fun getToken(): Flow<String> {
        return context.dataStore.data.map { setting ->
            setting[TOKEN] ?: ""
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("token")

    }
}