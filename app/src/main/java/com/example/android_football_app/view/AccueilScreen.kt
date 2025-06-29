package com.example.android_football_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccueilScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF23395d)), // Un joli bleu nuit, tu peux changer la couleur !
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Bienvenue sur Football App !",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Recherchez facilement des joueurs de football du monde entier !\n\nUtilisez la loupe ci-dessous pour commencer.",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 350.dp)
            )
        }
    }
}