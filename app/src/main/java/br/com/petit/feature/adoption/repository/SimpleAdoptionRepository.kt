package br.com.petit.feature.adoption.repository

import br.com.petit.feature.adoption.model.Adoption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SimpleAdoptionRepository : AdoptionRepository {
    private val adoption = MutableStateFlow<Adoption?>(null)
    override fun getAdoptedPet(): Flow<Adoption?> = adoption

    override suspend fun adopt(adoption: Adoption) {
        this.adoption.emit(adoption)
    }

    override suspend fun cancelAdoption() {
        this.adoption.emit(null)
    }
}
