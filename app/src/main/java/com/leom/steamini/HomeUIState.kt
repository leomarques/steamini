package com.leom.steamini

data class HomeUIState(
    val steamId: String = "",
    val games: List<String> = emptyList(),
    val showMostPlayed: Boolean = false,
    val geminiResponse: String = "",
)
