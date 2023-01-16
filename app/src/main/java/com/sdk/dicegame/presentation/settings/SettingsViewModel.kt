package com.sdk.dicegame.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.dicegame.manager.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
            manager.apply {
                get(DataStoreManager.fs).collect { b ->
                    _state.update {
                        SettingsState(
                            firstStart = b
                        )
                    }
                    println("@@@ViewM$b")
                }
//                get(DataStoreManager.pw).collect {
//                    _state.value = _state.value.copy(playWith = it)
//                }
//                get(DataStoreManager.sn).collect {
//                    _state.value = _state.value.copy(sound = it)
//                }
//                get(DataStoreManager.fc).collect {
//                    _state.value = _state.value.copy(finishCount = it)
//                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnFirstStart -> {
                viewModelScope.launch {
                    manager.save(DataStoreManager.fs, !event.boolean)
                    println("@@@${!event.boolean}")
                }
            }
            is SettingsEvent.OnPlayWith -> {
                viewModelScope.launch {
                    manager.save(DataStoreManager.pw, !event.boolean)
                }
            }
            is SettingsEvent.OnSound -> {
                viewModelScope.launch {
                    manager.save(DataStoreManager.sn, !event.boolean)
                }
            }
            is SettingsEvent.OnFinishCount -> {
                viewModelScope.launch {
                    manager.save(DataStoreManager.fc, !event.boolean)
                }
            }
        }
    }
}