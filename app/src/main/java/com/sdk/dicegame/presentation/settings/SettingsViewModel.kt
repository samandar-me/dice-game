package com.sdk.dicegame.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.dicegame.manager.DataStoreManager
import com.sdk.dicegame.manager.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val manager: DataStoreManager,
) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> get() = _state

    init {
        getAllSettingsState()
    }

    private fun getAllSettingsState() {
        viewModelScope.launch {
            manager.getState().collect { newState ->
                _state.update {
                    SettingsState(
                        firstStart = newState.firstStart,
                        playWith = newState.playWith,
                        sound = newState.sound,
                        finishCount = newState.finishCount
                    )
                }
            }
        }
    }

    fun saveState(event: SettingsEvent) {
        viewModelScope.launch {
            when (event) {
                is SettingsEvent.OnFirstStart -> {
                    manager.saveState(
                        State(
                            firstStart = !_state.value.firstStart,
                            playWith = _state.value.playWith,
                            sound = _state.value.sound,
                            finishCount = _state.value.finishCount,
                        )
                    )
                }
                is SettingsEvent.OnPlayWith -> {
                    manager.saveState(
                        State(
                            firstStart = _state.value.firstStart,
                            playWith = !_state.value.playWith,
                            sound = _state.value.sound,
                            finishCount = _state.value.finishCount,
                        )
                    )
                }
                is SettingsEvent.OnSound -> {
                    manager.saveState(
                        State(
                            firstStart = _state.value.firstStart,
                            playWith = _state.value.playWith,
                            sound = !_state.value.sound,
                            finishCount = _state.value.finishCount,
                        )
                    )
                }
                is SettingsEvent.OnFinishCount -> {
                    manager.saveState(
                        State(
                            firstStart = _state.value.firstStart,
                            playWith = _state.value.playWith,
                            sound = _state.value.sound,
                            finishCount = !_state.value.finishCount,
                        )
                    )
                }
            }
        }
    }
}