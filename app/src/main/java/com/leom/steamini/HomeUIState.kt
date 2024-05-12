package com.leom.steamini

data class HomeUIState(
    val steamId: String = "",
    val games: List<String> = emptyList(),
    val showMostPlayed: Boolean = false,
    val showButton: Boolean = true,
    val showSteamLoading: Boolean = false,
    val showWait: Boolean = false,
    val showGeminiResponse: Boolean = false,
    val geminiResponse: String = "",
    val error: String = "",
)
