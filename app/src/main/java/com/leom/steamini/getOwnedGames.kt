package com.leom.steamini

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun getBestGames(steamUserId: String): List<String> {
    val steamApiKey = BuildConfig.STEAM_API_KEY
    val url =
        "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/" +
            "?key=$steamApiKey" +
            "&steamid=$steamUserId" +
            "&include_appinfo=true" +
            "&format=json"

    val request =
        Request.Builder()
            .url(url)
            .build()

    val response = OkHttpClient().newCall(request).execute()

    if (!response.isSuccessful) {
        throw IOException("Failed to retrieve data: ${response.code}")
    }

    val responseBody = response.body!!.string()
    val gson = Gson()

    val responseObject = gson.fromJson(responseBody, SteamResponse::class.java)

    return responseObject.response.games
        .associate { it.name to it.playtimeForever }
        .toList()
        .sortedByDescending { it.second }
        .take(10)
        .map { it.first }
}
