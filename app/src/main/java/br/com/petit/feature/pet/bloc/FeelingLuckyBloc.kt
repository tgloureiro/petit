package br.com.petit.feature.pet.bloc

import br.com.petit.feature.pet.model.Pet
import kotlinx.coroutines.CoroutineScope
import tech.tiagoloureiro.bloc.Bloc

class FeelingLuckyBloc
constructor(
    private val scope: CoroutineScope,
) : Bloc<FeelingLuckyState, FeelingLuckyEvent>(NoPet, scope) {

  override suspend fun mapEventToState(
      event: FeelingLuckyEvent,
      state: FeelingLuckyState,
      emit: suspend (state: FeelingLuckyState) -> Unit
  ) {
    when (event) {
      is Next -> {
        val pets = event.pets
          if(pets.isNotEmpty()){
              val petId = pets.random().id
              emit(RandomPetId(petId))
          }else{
              emit(NoPet)
          }
      }
    }
  }
}

/*
 * FeelingLuckyStates
 *
 */
sealed class FeelingLuckyState

object NoPet : FeelingLuckyState()

data class RandomPetId(val petId: Long) : FeelingLuckyState()

/*
 * PetListEvents
 *
 */
sealed class FeelingLuckyEvent

data class Next(val pets: List<Pet>) : FeelingLuckyEvent()
