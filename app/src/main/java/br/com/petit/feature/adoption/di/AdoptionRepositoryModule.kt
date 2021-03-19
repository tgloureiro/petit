package br.com.petit.feature.adoption.di

import br.com.petit.core.repository.AppDatabase
import br.com.petit.feature.adoption.repository.AdoptionDao
import br.com.petit.feature.adoption.repository.AdoptionRepository
import br.com.petit.feature.adoption.repository.SimpleAdoptionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AdoptionRepositoryModule {
    @Provides
    fun provideAdoptionDao(appDatabase: AppDatabase): AdoptionDao {
        return appDatabase.adoptionDao()
    }

    /*@Provides
    @Singleton
    fun provideAdoptionRepository(adoptionDao: AdoptionDao): AdoptionRepository {
        return RoomAdoptionRepository(adoptionDao)
    }*/

    @Provides
    @Singleton
    fun provideAdoptionRepository(): AdoptionRepository {
        return SimpleAdoptionRepository()
    }
}
