package com.sdk.dicegame.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Game_Manager")
    companion object {
        val fs = booleanPreferencesKey(name = "first_start")
        val pw = booleanPreferencesKey(name = "play_with")
        val sn = booleanPreferencesKey(name = "sound")
        val fc = booleanPreferencesKey(name = "finish_count")
    }
    suspend fun save(key: Preferences.Key<Boolean>, boolean: Boolean) {
        context.dataStore.edit {
            it[key] = boolean
        }
    }
    fun get(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.dataStore.data.map {
            it[key] ?: false
        }
    }
}