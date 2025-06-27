package com.example.android_football_app.repository

import com.example.android_football_app.model.ApiResponse
import com.example.android_football_app.model.ApiTeamsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val API_FOOTBALL_KEY = "527514efd43ce9e12f499c05e861ee58"

interface ApiFootballService {
    @Headers(
        "x-apisports-key: $API_FOOTBALL_KEY"
    )
    @GET("players/profiles")
    suspend fun searchPlayers(
        @Query("search") name: String,
        @Query("page") page: Int = 1
    ): ApiResponse
    @GET("players/profiles")
    suspend fun getPlayerProfile(
        @Query("player") playerId: Int
    ): ApiResponse
    @GET("players/teams")
    suspend fun getPlayerTeams(
        @Query("player") playerId: Int
    ): ApiTeamsResponse
}