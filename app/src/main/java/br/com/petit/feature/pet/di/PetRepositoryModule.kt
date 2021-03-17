package br.com.petit.feature.pet.di

import br.com.petit.core.repository.AppDatabase
import br.com.petit.feature.pet.repository.PetDao
import br.com.petit.feature.pet.repository.PetRepository
import br.com.petit.feature.pet.repository.RoomPetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PetRepositoryModule {

    @Provides
    fun providePetDao(appDatabase: AppDatabase): PetDao {
        return appDatabase.petDao()
    }

    @Provides
    @Singleton
    fun providePetRepository(petDao: PetDao): PetRepository {
        return RoomPetRepository(petDao)
    }
}
