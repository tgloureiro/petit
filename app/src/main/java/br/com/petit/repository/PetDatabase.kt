package br.com.petit.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.petit.model.Adoption
import br.com.petit.model.Pet

@Database(entities = [Pet::class, Adoption::class], version = 1)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}