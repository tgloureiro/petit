package br.com.petit.feature.petDetails.bloc

import br.com.petit.feature.pet.bloc.PetListBloc
import br.com.petit.feature.pet.bloc.PetListLoaded
import br.com.petit.feature.pet.bloc.PetListLoading
import br.com.petit.feature.pet.model.Pet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.tiagoloureiro.bloc.Bloc

class PetBloc
constructor(
    private val petId: Long?,
    petListBloc: PetListBloc,
    scope: CoroutineScope,
) : Bloc<PetState, PetEvent>(PetLoading, scope) {

    init{
        petListBloc
            .state
            .onEach { state ->
                when (state) {
                    is PetListLoaded -> {
                        if (petId == null) {
                            add(PetNotFoundEvent)
                        } else {
                            val pet = state.pets.firstOrNull { pet -> pet.id == petId }
                            if (pet != null) {
                                add(LoadedEvent(pet))
                            } else {
                                add(PetNotFoundEvent)
                            }
                        }
                    }
                    PetListLoading -> {
                        add(LoadingEvent)
                    }
                }
            }
            .launchIn(scope)
    }


    override suspend fun mapEventToState(
        event: PetEvent,
        state: PetState,
        emit: suspend (state: PetState) -> Unit
    ) {
        when (event) {
            is LoadedEvent -> {
                emit(PetLoaded(event.pet))
            }
            LoadingEvent -> {
                emit(PetLoading)
            }
            PetNotFoundEvent -> {
                emit(PetNotFound)
            }
        }
    }
}

/*
 * PetStates
 *
 */

sealed class PetState

object PetLoading : PetState()

data class PetLoaded(val pet: Pet) : PetState()

object PetNotFound : PetState()

/*
 * PetEvents
 *
 */

sealed class PetEvent

private data class LoadedEvent(val pet: Pet) : PetEvent()

private object LoadingEvent : PetEvent()

private object PetNotFoundEvent : PetEvent()
