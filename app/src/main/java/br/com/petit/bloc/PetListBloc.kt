package br.com.petit.bloc

import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.tiagoloureiro.bloc.Bloc
import javax.inject.Inject

sealed class PetListState
object PetListLoading : PetListState()
data class PetListLoaded(val pets : List<Pet>) : PetListState()

sealed class PetListEvent
data class ListLoaded(val pets: List<Pet>) : PetListEvent()


class PetListBloc @Inject constructor(
    petRepository: PetRepository,
    scope: CoroutineScope,
    ) : Bloc<PetListState,PetListEvent>(PetListLoading) {

    private val job = petRepository.fetchPets().onEach {
        pets ->
        delay(3000)
        add(ListLoaded(pets))
    }.launchIn(scope)

    override fun mapEventToState(event: PetListEvent, state: PetListState): PetListState {
        when(event){
            is ListLoaded -> {
                return PetListLoaded(event.pets)
            }
        }
    }
}