package negara.asean.aseancountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import negara.asean.aseancountry.data.Repository
import negara.asean.aseancountry.model.Country

class JetCountryViewModel(private val repository: Repository) : ViewModel() {
    private val _sortedCountry = MutableStateFlow(
        repository.getCountry()
            .sortedBy { it.name }
    )
    val sortedHeroes: MutableStateFlow<List<Country>> get() = _sortedCountry
}

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetCountryViewModel::class.java)) {
            return JetCountryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}