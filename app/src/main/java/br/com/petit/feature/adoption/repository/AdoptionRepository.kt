package br.com.petit.feature.adoption.repository

import br.com.petit.feature.adoption.model.Adoption
import kotlinx.coroutines.flow.Flow

interface AdoptionRepository {
    fun getAdoptedPet(): Flow<Adoption?>
    suspend fun adopt(adoption: Adoption)
    suspend fun cancelAdoption()
}
