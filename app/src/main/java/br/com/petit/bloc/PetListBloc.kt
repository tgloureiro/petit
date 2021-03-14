package br.com.petit.bloc

import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import tech.tiagoloureiro.bloc.Bloc
import javax.inject.Inject
import javax.inject.Singleton

sealed class PetListState
object PetListLoading : PetListState()
data class PetListLoaded(val pets : List<Pet>) : PetListState()

sealed class PetListEvent
private data class ListLoaded(val pets: List<Pet>) : PetListEvent()

@Singleton
class PetListBloc @Inject constructor(
    petRepository: PetRepository,
    scope: CoroutineScope,
    ) : Bloc<PetListState, PetListEvent>(PetListLoading) {

    private val job = petRepository.fetchPets().onEach {
        pets ->
        delay(3000)
        println("Debug: Add List Lodaded PetLisBloc")
        add(ListLoaded(pets))
    }.launchIn(scope)

    override fun mapEventToState(
        event: PetListEvent,
        state: PetListState,
        emit: (state: PetListState) -> Unit) {

        when(event){
            is ListLoaded -> {
                println("Debug: mapEventToState State Emitted")
                emit(PetListLoaded(event.pets))
            }
        }
    }

}
