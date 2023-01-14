package com.sdk.dicegame.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val imageList: List<Int>
): ViewModel() {
    private val _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> get() = _gameState

    @OptIn(DelicateCoroutinesApi::class)
    val singleThread = newSingleThreadContext("ViewModel Single Thread")
    var rotation = 360f
    init {
        startImage()
    }

    private fun startImage() {
        viewModelScope.launch {
            _gameState.update {
                GameState(rotation = rotation)
            }
            while (rotation >= 0f) {
                delay(100L)
                rotation--
            }
        }
    }

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.Player1Clicked -> {
                viewModelScope.launch {
                    val r1 = randomNumber()
                    val r2 = randomNumber()
                    val image1 = randomImage(r1)
                    val image2 = randomImage(r2)
                    _gameState.update {
                        GameState(
                            player1Score = it.player1Score + r1 + r2,
                            im1 = image1,
                            im2 = image2,
                            currentScore = r1 + r2,
                            isButton1Enabled = false,
                            isButton2Enabled = true,
                            player2Score = it.player2Score
                        )
                    }
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
                            player2Score = it.player2Score + r1 + r2,
                            im1 = image1,
                            im2 = image2,
                            currentScore = r1 + r2,
                            isButton2Enabled = false,
                            isButton1Enabled = true,
                            player1Score = it.player1Score
                        )
                    }
                }
            }
        }
    }

    private suspend fun randomImage(index: Int): Int = withContext(singleThread) {
        imageList[index -1]
    }

    private suspend fun randomNumber(): Int = withContext(singleThread) {
        (1 until 7).random()
    }
}