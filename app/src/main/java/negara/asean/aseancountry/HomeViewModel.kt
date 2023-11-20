package negara.asean.aseancountry

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import negara.asean.aseancountry.data.Repository
import negara.asean.aseancountry.model.Country
import negara.asean.aseancountry.ui.common.UiState
import negara.asean.aseancountry.ui.screen.detail.DetailViewModel

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Country>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>>
        get() = _uiState

    fun getCountries() {
        viewModelScope.launch {
            repository.getCountry()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchCountry(newQuery)
                .catch { error ->
                    _uiState.value = UiState.Error(error.message.toString())
                }
                .collect { countyList ->
                    _uiState.value = UiState.Success(countyList)
                }
        }
    }
}

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}