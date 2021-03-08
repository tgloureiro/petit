package br.com.petit.di

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.room.Room
import br.com.petit.MainActivity
import br.com.petit.PetitApplication
import br.com.petit.repository.PetDao
import br.com.petit.repository.PetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun providePetDao(petDatabase: PetDatabase): PetDao {
        return petDatabase.petDao()
    }

    @Provides
    @Singleton
    fun providePetDatabase(@ApplicationContext appContext: Context): PetDatabase {
        return Room.databaseBuilder(
            appContext,
            PetDatabase::class.java,
            "Petit"
        ).build()
    }
}