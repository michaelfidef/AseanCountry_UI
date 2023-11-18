package negara.asean.aseancountry.di

import negara.asean.aseancountry.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}