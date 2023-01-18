package com.sdk.dicegame.presentation.computer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.dicegame.manager.DataStoreManager
import com.sdk.dicegame.manager.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ComputerViewModel @Inject constructor(
    private val imageList: List<Int>
) : ViewModel() {
    private val _gameState: MutableStateFlow<ComputerState> = MutableStateFlow(ComputerState())
    val gameState: StateFlow<ComputerState> get() = _gameState
    private var _rotateAnimation: MutableStateFlow<Float> = MutableStateFlow(180f)
    val rotateAnimation: StateFlow<Float> get() = _rotateAnimation
    private var _isGameFinished: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isGameFinished: StateFlow<Boolean> get() = _isGameFinished
    private var job: Job? = null
    private val state: MutableStateFlow<State> = MutableStateFlow(State())

    @OptIn(DelicateCoroutinesApi::class)
    val singleThread = newSingleThreadContext("Computer ViewModel Single Thread")


    fun onEvent(event: ComputerEvent) {
        when (event) {
            is ComputerEvent.PlayerClicked -> {
                viewModelScope.launch {
                    startImageAnimation()
                    val r1 = randomNumber()
                    val r2 = randomNumber()
                    val image1 = randomImage(r1)
                    val image2 = randomImage(r2)
                    _gameState.update {
                        ComputerState(
                            playerScore = it.playerScore + r1 + r2 + 2,
                            im1 = image1,
                            im2 = image2,
                            currentScore = r1 + r2 + 2,
                            isButton1Enabled = false,
                            isButton2Enabled = true,
                            computerScore = it.computerScore
                        )
                    }
                    checkGameFinishing()
                }
            }
            is ComputerEvent.ComputerClicked -> {
                if (!_isGameFinished.value) {
                    viewModelScope.launch {
                        startImageAnimation()
                        val r1 = randomNumber()
                        val r2 = randomNumber()
                        val image1 = randomImage(r1)
                        val image2 = randomImage(r2)
                        _gameState.update {
                            ComputerState(
                                computerScore = it.computerScore + r1 + r2 + 2,
                                im1 = image1,
                                im2 = image2,
                                currentScore = r1 + r2 + 2,
                                isButton2Enabled = false,
                                isButton1Enabled = true,
                                playerScore = it.playerScore
                            )
                        }
                        checkGameFinishing()
                    }
                }
            }
            is ComputerEvent.OkButtonClicked -> {
                startImageAnimation()
                _gameState.update {
                    ComputerState()
                }
                _isGameFinished.value = false
            }
        }
    }

    private fun startImageAnimation() {
        _rotateAnimation.value = 180f
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.Default) {
            while (_rotateAnimation.value > 0f) {
                delay(1L)
                _rotateAnimation.value--
            }
        }
    }

    private suspend fun checkGameFinishing() {
        delay(700L)
        val finishCount = if (state.value.finishCount) 100 else 50
        _isGameFinished.update {
            _gameState.value.playerScore >= finishCount
                    || _gameState.value.computerScore >= finishCount
        }
    }

    private suspend fun randomImage(index: Int): Int = withContext(singleThread) {
        imageList[index]
    }

    private suspend fun randomNumber(): Int = withContext(singleThread) {
        Random().nextInt(6)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}