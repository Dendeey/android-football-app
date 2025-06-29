package com.example.android_football_app.repository

import com.example.android_football_app.model.ApiResponse
import com.example.android_football_app.model.ApiTeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFootballService {
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
