package br.com.petit.bloc

import br.com.petit.model.Pet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.tiagoloureiro.bloc.Bloc
import javax.inject.Singleton

sealed class PetState
object PetLoading : PetState()
data class PetLoaded(val pet : Pet) : PetState()
object PetNotFound : PetState()


sealed class PetEvent
data class LoadedEvent(val pet: Pet) : PetEvent()
object LoadingEvent : PetEvent()
object PetNotFoundEvent : PetEvent()


sealed class PetIdArg
data class ValidPetId(val petId: Long) : PetIdArg()
object NoPetId: PetIdArg()

class PetBloc constructor(
    private val petIdArg: PetIdArg,
    petListBloc: PetListBloc,
    scope: CoroutineScope,
    ) : Bloc<PetState,PetEvent>(PetLoading) {

    private val job = petListBloc.state.onEach {
        state ->
        when(state){
            is PetListLoaded -> {
                val pet = state.pets.firstOrNull {
                        pet->
                    pet.id == (petIdArg as ValidPetId).petId
                }
                if(pet!=null) {
                    add(LoadedEvent(pet))
                }else{
                    add(PetNotFoundEvent)
                }
            }
            PetListLoading -> {
                add(LoadingEvent)
            }
        }
    }.launchIn(scope)

    override fun mapEventToState(
        event: PetEvent,
        state: PetState,
        emit: (state: PetState) -> Unit
    ) {
        when(event){
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