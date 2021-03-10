package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger

class SuccessfulAdoptedScreenViewModel() : ViewModel() {
/*
    private val  _pet = MutableStateFlow<Pet?>(null)
    val pet: StateFlow<Pet?> = _pet

    private val job = petRepository.fetchPet(petId).onEach {
        _pet.emit(it)
    }.launchIn(viewModelScope)

    init{
        Logger.getLogger("SuccessfulAdoptedScreenViewModel").log(
            Level.INFO, "SuccessfulAdoptedScreenViewModel created")
    }

    fun onBackPress(){
        navigator.emitEvent(PopRoute)
    }

    fun onCancelButtonClick(){
        navigator.emitEvent(PopRoute)
        viewModelScope.launch {
            petRepository.adopt(Adoption(0, null))
        }
    }

    override fun onCleared() {
        super.onCleared()
        Logger.getLogger("SuccessfulAdoptedScreenViewModel").log(
            Level.INFO, "SuccessfulAdoptedScreenViewModel cleared!!!")
    }
*/
}
