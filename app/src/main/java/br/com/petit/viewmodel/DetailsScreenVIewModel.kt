package br.com.petit.viewmodel

import androidx.lifecycle.*
import br.com.petit.navigator.SuccessfulAdoptionRoute
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.tiagoloureiro.navigator.Navigate
import tech.tiagoloureiro.navigator.NavigatorBloc
import tech.tiagoloureiro.navigator.PopRoute
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

/*sealed class PetState
object Loading : PetState()
data class Loaded(val pet:Pet) : PetState()
*/

class DetailsScreenViewModel : ViewModel(), LifecycleObserver {

    /*
    init{
        Logger.getLogger("DetailsScreenViewModel").log(
            Level.INFO, "DetailsScreenViewModel created")
    }


    private val  _pet = MutableStateFlow<PetState>(Loading)
    val pet: StateFlow<PetState> = _pet

    private val job = petRepository.fetchPet(petId).onEach {
        delay(3000)
        _pet.emit(Loaded(it))
    }.launchIn(viewModelScope)

    fun onBackPress(){
        navigator.add(PopRoute)
    }

    fun onAdoptButtonClick(){
        val value = pet.value
        if(value is Loaded){
            viewModelScope.launch {
                petRepository.adopt(Adoption(0, value.pet))
                navigator.add(Navigate(SuccessfulAdoptionRoute(petId)))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Logger.getLogger("DetailsScreenViewModel").log(
            Level.INFO, "DetailsScreenViewModel cleared!!!")
    }

*/
}
/*
class DetailsScreenViewModelFactory(private val petId: Long, private val petRepository: PetRepository,
                           private val navigator: NavigatorBloc
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsScreenViewModel(petId,navigator,petRepository) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}
 */