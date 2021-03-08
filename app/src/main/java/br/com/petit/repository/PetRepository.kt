package br.com.petit.repository

import androidx.room.Insert
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository{
    suspend fun  insertPet(pet: Pet)
    fun fetchPets(): Flow<List<Pet>>
    suspend fun getAdoptedPet(): Adoption?
    fun adopt(adoption: Adoption)
}