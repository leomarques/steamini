package com.leom.steamini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun onSendToGemini() {
        _uiState.value = uiState.value.copy(
            showButton = false,
            showMostPlayed = false,
            showSteamLoading = true,
            error = ""
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val games = getBestGames(uiState.value.steamId)
                    _uiState.value =
                        uiState.value.copy(
                            games = games,
                            showMostPlayed = true,
                            showSteamLoading = false,
                            showWait = true
                        )

                    val response = sendToGemini(games) ?: ""
                    _uiState.value =
                        uiState.value.copy(
                            showWait = false,
                            showGeminiResponse = true,
                            geminiResponse = response,
                        )
                } catch (e: IOException) {
                    _uiState.value = uiState.value.copy(
                        error = e.message ?: "Error",
                        showButton = true,
                        showSteamLoading = false
                    )
                }
            }
        }
    }

    fun onValueChange(id: String) {
        _uiState.value = uiState.value.copy(steamId = id)
    }
}
