package negara.asean.aseancountry.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import negara.asean.aseancountry.data.Repository
import negara.asean.aseancountry.model.Country
import negara.asean.aseancountry.ui.common.UiState

class DetailViewModel (private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Country>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>>
        get() = _uiState

    fun GetCountrybyId(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val music = repository.getCountrybyId(id)
                _uiState.value = UiState.Success(listOf(music))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}