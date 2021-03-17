package br.com.petit.feature.adoption.bloc

import br.com.petit.feature.adoption.model.Adoption
import br.com.petit.feature.adoption.repository.AdoptionRepository
import br.com.petit.feature.pet.model.Pet
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.tiagoloureiro.bloc.Bloc

@Singleton
class AdoptionBloc
@Inject
constructor(
    private val adoptionRepository: AdoptionRepository,
    private val scope: CoroutineScope,
) : Bloc<AdoptionState, AdoptionEvent>(AdoptionLoading, scope) {

    private val job =
        adoptionRepository
            .getAdoptedPet()
            .onEach { adoption -> add(UpdateAdoption(adoption)) }
            .launchIn(scope)

    override suspend fun mapEventToState(
        event: AdoptionEvent,
        state: AdoptionState,
        emit: suspend (state: AdoptionState) -> Unit
    ) {
        when (event) {
            is Adopt -> {
                val pet = event.pet
                adoptionRepository.adopt(Adoption(0, pet))
            }
            CancelAdoption -> {
                adoptionRepository.cancelAdoption()
            }
            is UpdateAdoption -> {
                if (event.adoption == null) {
                    emit(NoAdoption)
                } else {
                    emit(ValidAdoption(event.adoption))
                }
            }
        }
    }
}

/*
 * AdoptionState
 *
 */

sealed class AdoptionState

object AdoptionLoading : AdoptionState()

data class ValidAdoption(val adoption: Adoption) : AdoptionState()

object NoAdoption : AdoptionState()

/*
 * PetEvents
 *
 */

sealed class AdoptionEvent

private data class UpdateAdoption(val adoption: Adoption?) : AdoptionEvent()

data class Adopt(val pet: Pet) : AdoptionEvent()

object CancelAdoption : AdoptionEvent()
