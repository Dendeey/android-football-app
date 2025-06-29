package com.example.android_football_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_football_app.view.AccueilScreen
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
                                label = { Text("Accueil") },
                                selected = currentRoute == "accueil",
                                onClick = { navController.navigate("accueil") }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Search, contentDescription = "Recherche") },
                                label = { Text("Recherche") },
                                selected = currentRoute == "home",
                                onClick = { navController.navigate("home") }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "accueil",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("accueil") {
                            AccueilScreen()
                        }
                        composable("home") {
                            HomeScreen(
                                players = players,
                                onSearch = { query -> playerViewModel.search(query) },
                                onPlayerClick = { playerId ->
                                    navController.navigate("player/$playerId")
                                },
                                loading = loading,
                                error = error,
                                loadDefault = { playerViewModel.loadRandomOrFirstPlayer() },
                                modifier = Modifier
                            )
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
}