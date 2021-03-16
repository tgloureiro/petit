package br.com.petit.repository

import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocalPetRepository @Inject constructor(private val petDao: PetDao) : PetRepository {
    override suspend fun insertPet(pet: Pet) = petDao.insertAll(pet)
    override fun fetchPets() = petDao.getAll()
    override fun fetchPet(petId: Long): Flow<Pet> = petDao.fetchPet(petId)
    override suspend fun getAdoptedPet(): Adoption? = petDao.getAdoptedPet()
    override suspend fun adopt(adoption: Adoption) = petDao.adopt(adoption)
}
