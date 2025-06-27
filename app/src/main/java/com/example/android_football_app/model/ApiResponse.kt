package com.example.android_football_app.model


data class ApiResponse(
    val get: String?,
    val parameters: Map<String, String>?,
    val errors: List<String>?,
    val results: Int?,
    val paging: Paging?,
    val response: List<ApiPlayerResponse>
)

data class Paging(
    val current: Int?,
    val total: Int?
)

data class ApiPlayerResponse(
    val player: ApiPlayer?,
)

data class ApiPlayer(
    val id: Int?,
    val name: String?,
    val firstname: String?,
    val lastname: String?,
    val age: Int?,
    val birth: Birth?,
    val nationality: String?,
    val height: String?,
    val weight: String?,
    val number: Int?,
    val position: String?,
    val photo: String?
)

data class Birth(
    val date: String?,
    val place: String?,
    val country: String?
)

data class ApiTeamsResponse(
    val response: List<ApiTeamEntry>
)

data class ApiTeamEntry(
    val team: TeamInfo,
    val season: Int
)

data class TeamInfo(
    val id: Int?,
    val name: String?
)