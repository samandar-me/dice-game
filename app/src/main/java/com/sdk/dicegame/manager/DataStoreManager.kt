package com.sdk.dicegame.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "GameManager")
    companion object {
        val fs = booleanPreferencesKey(name = "first_start")
        val pw = booleanPreferencesKey(name = "play_with")
        val sn = booleanPreferencesKey(name = "sound")
        val fc = booleanPreferencesKey(name = "finish_count")
    }
    suspend fun saveState(state: State) {
        context.dataStore.edit {
            it[fs] = state.firstStart
            it[pw] = state.playWith
            it[sn] = state.sound
            it[fc] = state.finishCount
        }
    }
    fun getState(): Flow<State> {
        return context.dataStore.data.map {
            State(
                firstStart = it[fs] ?: false,
                playWith = it[pw] ?: false,
                sound = it[sn] ?: false,
                finishCount = it[fc] ?: false
            )
        }
    }
}