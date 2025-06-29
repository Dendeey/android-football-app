package com.example.android_football_app.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import com.example.android_football_app.R
import com.example.android_football_app.model.Player

@Composable
fun HomeScreen(
    players: List<Player>,
    onSearch: (String) -> Unit,
    onPlayerClick: (Int) -> Unit,
    loading: Boolean,
    error: String?,
    modifier: Modifier = Modifier,
    loadDefault: (() -> Unit)? = null

) {
    var searchQuery by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (players.isEmpty() && loadDefault != null) {
            loadDefault()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barre de recherche
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(stringResource(id = R.string.search_player)) }, // internationalisation
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (searchQuery.trim().isNotEmpty()) {
                    showError = false
                    onSearch(searchQuery)
                } else {
                    showError = true
                }
            },
            modifier = Modifier.align(Alignment.End),
        ) {
            Text(text = stringResource(id = R.string.search))
        }
        if (showError) {
            Text(
                text = "Veuillez entrer un nom de joueur avant de rechercher.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Affichage loading
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Affichage erreur
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Liste des joueurs (seulement si pas loading et pas erreur)
        if (!loading && error == null) {
            if (players.isEmpty() && searchQuery.isNotBlank()) {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn {
                    items(players) { player ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPlayerClick(player.id) }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
// Affiche la photo du joueur (si dispo)
                            AsyncImage(
                                model = player.photo,
                                contentDescription = "Photo de ${player.name}",
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 12.dp)
                            )

                            Column {
                                Text(
                                    text = player.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = player.team ?: "Équipe inconnue",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Divider()
                    }
                }
            }
        }
    }
}