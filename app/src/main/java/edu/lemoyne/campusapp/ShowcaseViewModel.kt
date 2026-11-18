package edu.lemoyne.campusapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ShowcaseUIStates {
    data object Loading: ShowcaseUIStates()
    data class Success(val item: List<ShowcaseItem>): ShowcaseUIStates()
    data class Error(val message: String): ShowcaseUIStates()
}

class ShowcaseViewModel: ViewModel() {
    private val repository = ShowcaseRepository()


    private val _uiState = MutableStateFlow<ShowcaseUIStates>(ShowcaseUIStates.Loading)
    val uiState: StateFlow<ShowcaseUIStates> = _uiState

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = ShowcaseUIStates.Loading
            try {
                val items = repository.loadItems()
                _uiState.value = ShowcaseUIStates.Success(items)
            } catch(error: Exception) {
                _uiState.value = ShowcaseUIStates.Error(error.message ?: "Network error")
            }
        }
    }
}