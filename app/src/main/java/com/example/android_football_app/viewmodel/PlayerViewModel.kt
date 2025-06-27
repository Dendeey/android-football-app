package com.example.android_football_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_football_app.model.Player
import com.example.android_football_app.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {
    private val repository = PlayerRepository()

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun search(query: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val result = repository.searchPlayers(query)
                _players.value = result
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Erreur inconnue"
                _players.value = emptyList()
            }
            _loading.value = false
        }
    }
}