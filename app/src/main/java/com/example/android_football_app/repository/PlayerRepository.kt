package com.example.android_football_app.repository

import com.example.android_football_app.model.ApiPlayer
import com.example.android_football_app.model.Player
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerRepository {
    private val api: ApiFootballService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v3.football.api-sports.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiFootballService::class.java)
    }

    suspend fun searchPlayers(query: String): List<Player> {
        val result = api.searchPlayers(query)
        return result.response.mapNotNull { playerResp ->
            val p = playerResp.player
            if (p?.id != null && p.name != null) {
                Player(
                    id = p.id,
                    name = listOfNotNull(p.firstname, p.lastname).joinToString(" "),
                    team = "",   // Remplacer par club si dispo
                    photo = p.photo
                )
            } else null
        }
    }

    suspend fun getPlayerDetail(playerId: Int): ApiPlayer? {
        val result = api.getPlayerProfile(playerId)
        return result.response.firstOrNull()?.player
    }
}