package negara.asean.aseancountry.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import negara.asean.aseancountry.model.CountriesData
import negara.asean.aseancountry.model.CountriesData.countries
import negara.asean.aseancountry.model.Country

class Repository {
    fun getCountry(): Flow<List<Country>> {
        return flowOf(countries)
    }

    fun searchCountry(query: String): Flow<List<Country>> {
        return flowOf(CountriesData.countries.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    fun getCountrybyId(id: Long): Country {
        return countries.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}