package com.leom.steamini

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val games = getBestGames()
                Log.d("gemini", "Games: ${games.joinToString(separator = "\n")}")

                val response = sendToGemini(games)
                Log.d("gemini", response ?: "No response")
            }
        }
    }
}
