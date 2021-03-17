package br.com.petit.feature.pet.repository

import br.com.petit.feature.pet.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    suspend fun insertPet(pet: Pet)
    fun fetchPets(): Flow<List<Pet>>
    fun fetchPet(petId: Long): Flow<Pet>
}
