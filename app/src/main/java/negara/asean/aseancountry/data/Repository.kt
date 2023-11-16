package negara.asean.aseancountry.data

import negara.asean.aseancountry.model.CountriesData
import negara.asean.aseancountry.model.Country

class Repository {
    fun getCountry(): List<Country> {
        return CountriesData.countries
    }
}