package br.com.petit.repository

import androidx.room.*
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getAll(): Flow<List<Pet>>

    @Query("SELECT * FROM pet WHERE id = :petId LIMIT 1")
    fun fetchPet(petId: Long): Flow<Pet>

    @Transaction
    @Query("SELECT * FROM Adoption LIMIT 1")
    suspend fun getAdoptedPet(): Adoption?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adopt(adoption: Adoption)

    @Insert
    fun insertAll(vararg users: Pet)

    @Delete
    fun delete(pet: Pet)
}