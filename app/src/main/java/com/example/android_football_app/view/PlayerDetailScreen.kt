package com.example.android_football_app.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_football_app.model.ApiPlayer
import com.example.android_football_app.repository.PlayerRepository

@Composable
fun PlayerDetailScreen(
    playerId: Int,
    repository: PlayerRepository = PlayerRepository()
) {
    var player by remember { mutableStateOf<ApiPlayer?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Charge les détails dès l'arrivée sur la page
    LaunchedEffect(playerId) {
        loading = true
        error = null
        try {
            player = repository.getPlayerDetail(playerId)
        } catch (e: Exception) {
            error = e.localizedMessage
        }
        loading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }
            error != null -> {
                Text(
                    text = "Erreur : $error",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            player != null -> {
                PlayerDetailContent(player = player!!)
            }
        }
    }
}

@Composable
private fun PlayerDetailContent(player: ApiPlayer) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = player.photo,
            contentDescription = "Photo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "${player.firstname.orEmpty()} ${player.lastname.orEmpty()}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        InfoLine(label = "Âge", value = player.age?.toString())
        InfoLine(label = "Date de naissance", value = player.birth?.date)
        InfoLine(label = "Lieu de naissance", value = player.birth?.place)
        InfoLine(label = "Pays de naissance", value = player.birth?.country)
        InfoLine(label = "Nationalité", value = player.nationality)
        InfoLine(label = "Taille", value = player.height)
        InfoLine(label = "Poids", value = player.weight)
        InfoLine(label = "Numéro", value = player.number?.toString())
        InfoLine(label = "Position", value = player.position)
    }
}

@Composable
private fun InfoLine(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label :", fontWeight = FontWeight.SemiBold)
        Text(text = value ?: "-", fontWeight = FontWeight.Normal)
    }
}
