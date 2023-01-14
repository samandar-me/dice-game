package com.sdk.dicegame.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val imageList: List<Int>,
) : ViewModel() {
    private val _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> get() = _gameState
    private var _rotateAnimation: MutableStateFlow<Float> = MutableStateFlow(180f)
    val rotateAnimation: StateFlow<Float> get() = _rotateAnimation
    private var _isGameFinished: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isGameFinished: StateFlow<Boolean> get() = _isGameFinished
    private var job: Job? = null
    private val finishNumber = 20

    @OptIn(DelicateCoroutinesApi::class)
    val singleThread = newSingleThreadContext("ViewModel Single Thread")

    fun onEvent(event: GameEvent) {
        startImageAnimation()
        when (event) {
            is GameEvent.Player1Clicked -> {
                viewModelScope.launch {
                    val r1 = randomNumber()
                    val r2 = randomNumber()
                    val image1 = randomImage(r1)
                    val image2 = randomImage(r2)
                    _gameState.update {
                        GameState(
                            player1Score = it.player1Score + r1 + r2 + 2,
                            im1 = image1,
                            im2 = image2,
                            currentScore = r1 + r2 + 2,
                            isButton1Enabled = false,
                            isButton2Enabled = true,
                            player2Score = it.player2Score
                        )
                    }
                    checkGameFinishing()
                }
            }
            is GameEvent.Player2Clicked -> {
                viewModelScope.launch {
                    val r1 = randomNumber()
                    val r2 = randomNumber()
                    val image1 = randomImage(r1)
                    val image2 = randomImage(r2)
                    _gameState.update {
                        GameState(
                            player2Score = it.player2Score + r1 + r2 + 2,
                            im1 = image1,
                            im2 = image2,
                            currentScore = r1 + r2 + 2,
                            isButton2Enabled = false,
                            isButton1Enabled = true,
                            player1Score = it.player1Score
                        )
                    }
                    checkGameFinishing()
                }
            }
            is GameEvent.OkButtonClicked -> {
                _gameState.update {
                    GameState()
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
        _isGameFinished.update {
            _gameState.value.player1Score >= finishNumber
                    || _gameState.value.player2Score >= finishNumber
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