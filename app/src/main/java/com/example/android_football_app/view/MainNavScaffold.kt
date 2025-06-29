package com.example.android_football_app.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestination(val route: String, val label: String, val icon: ImageVector) {
    object Home : NavDestination("home", "Accueil", Icons.Default.Home)
    object Search : NavDestination("search", "Recherche", Icons.Default.Search)
}