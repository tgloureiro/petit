package br.com.petit

import android.app.Application
import android.content.Context
import androidx.room.Room
import br.com.petit.repository.PetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, PetDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    /*@Provides
    @Singleton
    fun providePetDatabase(@ApplicationContext appContext: Context): PetDatabase {
        return Room.databaseBuilder(
            appContext,
            PetDatabase::class.java,
            "Petit"
        ).build()
    }*/

}
