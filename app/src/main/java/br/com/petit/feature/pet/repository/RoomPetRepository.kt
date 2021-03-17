package br.com.petit.feature.pet.repository

import br.com.petit.feature.pet.model.Pet
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RoomPetRepository @Inject constructor(private val petDao: PetDao) : PetRepository {
    override suspend fun insertPet(pet: Pet) = petDao.insertAll(pet)
    override fun fetchPets() = petDao.getAll()
    override fun fetchPet(petId: Long): Flow<Pet> = petDao.fetchPet(petId)
}
