package com.example.android_football_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.android_football_app.ui.theme.AndroidFootballAppTheme
import com.example.android_football_app.view.HomeScreen
import com.example.android_football_app.viewmodel.PlayerViewModel

// AJOUTS NAVIGATION COMPOSE :
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_football_app.view.PlayerDetailScreen

class MainActivity : ComponentActivity() {
    private val playerViewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidFootballAppTheme {
                val navController = rememberNavController()
                val players by playerViewModel.players.collectAsState()
                val loading by playerViewModel.loading.collectAsState()
                val error by playerViewModel.error.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            HomeScreen(
                                players = players,
                                onSearch = { query -> playerViewModel.search(query) },
                                onPlayerClick = { playerId ->
                                    navController.navigate("player/$playerId")
                                },
                                loading = loading,
                                error = error,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                    composable("player/{playerId}") { backStackEntry ->
                        val playerId = backStackEntry.arguments?.getString("playerId")?.toIntOrNull()
                        if (playerId != null) {
                            PlayerDetailScreen(playerId = playerId)
                        }
                    }
                }
            }
        }
    }
}