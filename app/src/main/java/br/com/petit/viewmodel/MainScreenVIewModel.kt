package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel : ViewModel() {

    private val petList = listOf(
        Pet( 0,"Rudy", "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg", PetGender.MALE, "Description"),
        Pet( 1,"Holly", "https://images.dog.ceo/breeds/kuvasz/n02104029_4091.jpg", PetGender.FEMALE, "Description"),
        Pet(2,"Maddie", "https://images.dog.ceo/breeds/cotondetulear/IMG_20160830_202631573.jpg", PetGender.FEMALE, "Description"),
        Pet(3,"Roxy", "https://images.dog.ceo/breeds/pointer-german/n02100236_1553.jpg", PetGender.FEMALE, "Description"),
        Pet(4,"Zoey", "https://images.dog.ceo/breeds/retriever-chesapeake/n02099849_264.jpg", PetGender.FEMALE, "Description"),
        Pet(5,"Duke", "https://images.dog.ceo/breeds/cattledog-australian/IMG_1688.jpg", PetGender.MALE, "Description"),
    )

    private val _pets = MutableStateFlow(petList)
    val pets: StateFlow<List<Pet>> = _pets

    fun deleteFirst(){
        val petList = pets.value.toMutableList()
        if(petList.isNotEmpty()) {
            petList.removeFirst()
            _pets.value = petList.toList()
        }
    }
}