package negara.asean.aseancountry.data

import negara.asean.aseancountry.model.CountriesData
import negara.asean.aseancountry.model.Country

class Repository {
    fun getCountry(): List<Country> {
        return CountriesData.countries
    }

    fun searchCountry(query: String): List<Country>{
        return CountriesData.countries.filter {
            it.name.contains(query, ignoreCase = true)
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