package negara.asean.aseancountry.model

data class Country(
    val id: Long,
    val name: String,
    val photo: Int,
    val ibuKota: String,
    val description: String,
)