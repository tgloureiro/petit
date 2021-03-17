package br.com.petit.feature.adoption.repository

import br.com.petit.feature.adoption.model.Adoption
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RoomAdoptionRepository @Inject constructor(private val adoptionDao: AdoptionDao) :
    AdoptionRepository {
    override fun getAdoptedPet(): Flow<Adoption?> = adoptionDao.getAdoptedPet()

    override suspend fun adopt(adoption: Adoption) = adoptionDao.adopt(adoption)

    override suspend fun cancelAdoption() = adoptionDao.cancelAdoption()
}
