package com.example.android_football_app.repository

import com.example.android_football_app.model.ApiPlayer
import com.example.android_football_app.model.Player
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Partie globale : logging et retrofit bien configurés
val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-apisports-key", "527514efd43ce9e12f499c05e861ee58") // Mets ta clé ici
            .build()
        chain.proceed(request)
    }
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://v3.football.api-sports.io/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

val api = retrofit.create(ApiFootballService::class.java)

class PlayerRepository {
    // Utilise directement l’api créée plus haut !
    private val api: ApiFootballService = com.example.android_football_app.repository.api

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
