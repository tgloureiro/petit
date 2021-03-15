package br.com.petit.bloc

import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    private val scope: CoroutineScope,
    ) : Bloc<PetListState, PetListEvent>(PetListLoading, scope) {

    private val job = petRepository.fetchPets().onEach {
        pets ->
        delay(3000)
        add(ListLoaded(pets))
    }.launchIn(scope)

    override suspend fun mapEventToState(
        event: PetListEvent,
        state: PetListState,
        emit: suspend (state: PetListState) -> Unit) {

        when(event){
            is ListLoaded -> {
                emit(PetListLoaded(event.pets))
            }
        }
    }
}
