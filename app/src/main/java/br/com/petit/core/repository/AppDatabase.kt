package br.com.petit.core.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.petit.feature.adoption.model.Adoption
import br.com.petit.feature.adoption.repository.AdoptionDao
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.repository.PetDao

@Database(entities = [Pet::class, Adoption::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun adoptionDao(): AdoptionDao
}
