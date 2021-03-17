package br.com.petit.feature.pet.repository

import androidx.room.*
import br.com.petit.feature.pet.model.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pet") fun getAll(): Flow<List<Pet>>
    @Query("SELECT * FROM pet WHERE id = :petId LIMIT 1") fun fetchPet(petId: Long): Flow<Pet>
    @Insert fun insertAll(vararg users: Pet)
    @Delete fun delete(pet: Pet)
}
