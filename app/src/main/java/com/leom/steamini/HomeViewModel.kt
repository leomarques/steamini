package com.leom.steamini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun onSendToGemini() {
        _uiState.value = uiState.value.copy(showMostPlayed = false)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val games = getBestGames(uiState.value.steamId)
                _uiState.value =
                    uiState.value.copy(
                        games = games,
                        showMostPlayed = true,
                    )

                val response = sendToGemini(games) ?: ""
                _uiState.value =
                    uiState.value.copy(
                        games = games,
                        showMostPlayed = true,
                        geminiResponse = response,
                    )
            }
        }
    }

    fun onValueChange(id: String) {
        _uiState.value = uiState.value.copy(steamId = id)
    }
}
