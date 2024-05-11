package com.leom.steamini

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig

suspend fun sendToGemini(games: List<String>): String? {

    val generativeModel = GenerativeModel(
        modelName = "gemini-1.0-pro",
        apiKey = BuildConfig.GEMINI_API_KEY,
        generationConfig = generationConfig {
            temperature = 0.7f
        }
    )

    val prompt =
        "Give me 5 game recommendations based on this list: ${
            games.joinToString(
                ", "
            )
        }, explain why you chose each one"
    val response = generativeModel.generateContent(prompt)

    return response.text
}
