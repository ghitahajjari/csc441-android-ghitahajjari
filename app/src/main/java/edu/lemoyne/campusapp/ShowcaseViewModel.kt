package edu.lemoyne.campusapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Class 21: Step 3: viewmodel
sealed class ShowcaseUiState {
    data object Loading: ShowcaseUiState()
    data class Success(val items: List<ShowcaseItem>): ShowcaseUiState()
    data class Error(val message: String): ShowcaseUiState()
}

class ShowcaseViewModel: ViewModel() {
    private val repository = ShowcaseRepository()


    private val _state = MutableStateFlow<ShowcaseUiState>(ShowcaseUiState.Loading)
    val state = _state.asStateFlow()
    private var accessToken: String? = null

    fun setAccessToken(token: String) {
        accessToken = token
    }

    fun refresh() {
        val token = accessToken ?: run{
            _state.value = ShowcaseUiState.Error("Please log in first")
        }

        viewModelScope.launch {
            _state.value = ShowcaseUiState.Loading
            try {
                val items = repository.loadItems(token as String)
                _state.value = ShowcaseUiState.Success(items)
            } catch(error: Exception) {
                _state.value = ShowcaseUiState.Error(error.message ?: "Network error")
            }
        }
    }
}