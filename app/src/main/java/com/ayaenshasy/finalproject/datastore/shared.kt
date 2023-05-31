package com.ayaenshasy.finalproject.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreData(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("myShared")
        val IS_FIRST_TIME_KEY = stringPreferencesKey("first_time")
    }

    // to get the data
    val getIsFirstTime: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[IS_FIRST_TIME_KEY]
        }

    // to save the data
    suspend fun saveIsFirstTime(name: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_KEY]=name
        }
    }
}