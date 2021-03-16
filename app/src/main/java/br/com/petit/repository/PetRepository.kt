package br.com.petit.repository

import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    suspend fun insertPet(pet: Pet)
    fun fetchPets(): Flow<List<Pet>>
    fun fetchPet(petId: Long): Flow<Pet>
    suspend fun getAdoptedPet(): Adoption?
    suspend fun adopt(adoption: Adoption)
}
