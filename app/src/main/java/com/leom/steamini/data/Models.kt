package com.leom.steamini.data

import com.google.gson.annotations.SerializedName

data class SteamResponse(
    val response: GamesResponse,
)

data class GamesResponse(
    @SerializedName("game_count")
    val gameCount: Long,
    val games: List<Game>,
)

data class Game(
    val appid: Long,
    val name: String,
    @SerializedName("playtime_forever")
    val playtimeForever: Long,
    @SerializedName("img_icon_url")
    val imgIconURL: String,
    @SerializedName("has_community_visible_stats")
    val hasCommunityVisibleStats: Boolean? = null,
    @SerializedName("playtime_windows_forever")
    val playtimeWindowsForever: Long,
    @SerializedName("playtime_mac_forever")
    val playtimeMACForever: Long,
    @SerializedName("playtime_linux_forever")
    val playtimeLinuxForever: Long,
    @SerializedName("playtime_deck_forever")
    val playtimeDeckForever: Long,
    @SerializedName("rtime_last_played")
    val rtimeLastPlayed: Long,
    @SerializedName("content_descriptorids")
    val contentDescriptorids: List<Long>? = null,
    @SerializedName("playtime_disconnected")
    val playtimeDisconnected: Long,
    @SerializedName("has_leaderboards")
    val hasLeaderboards: Boolean? = null,
    @SerializedName("playtime_2weeks")
    val playtime2Weeks: Long? = null,
)
