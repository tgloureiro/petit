package br.com.petit.repository

import androidx.room.*
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getAll(): Flow<List<Pet>>



    @Transaction
    @Query("SELECT * FROM Adoption LIMIT 1")
    suspend fun getAdoptedPet(): Adoption?

    @Insert
    fun adopt(adoption: Adoption)

    //@Query("SELECT * FROM pet WHERE id IN (:petIds)")
    //fun loadAllByIds(petIds: IntArray): List<Pet>

    //@Query("SELECT * FROM pet WHERE name LIKE :name LIMIT 1")
    //fun findByName(name: String): Pet

    @Insert
    fun insertAll(vararg users: Pet)

    @Delete
    fun delete(pet: Pet)
}