package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SuccessfulAdoptedScreenViewModel(private val petId: Long, private val navigationViewModel: NavigationViewModel) : ViewModel() {

    private val petList = listOf(
        Pet( 0,"Rudy", "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg", PetGender.MALE, "Description"),
        Pet( 1,"Holly", "https://images.dog.ceo/breeds/kuvasz/n02104029_4091.jpg", PetGender.FEMALE, "Description"),
        Pet(2,"Maddie", "https://images.dog.ceo/breeds/cotondetulear/IMG_20160830_202631573.jpg", PetGender.FEMALE, "Description"),
        Pet(3,"Roxy", "https://images.dog.ceo/breeds/pointer-german/n02100236_1553.jpg", PetGender.FEMALE, "Description"),
        Pet(4,"Zoey", "https://images.dog.ceo/breeds/retriever-chesapeake/n02099849_264.jpg", PetGender.FEMALE, "Description"),
        Pet(5,"Duke", "https://images.dog.ceo/breeds/cattledog-australian/IMG_1688.jpg", PetGender.MALE, "Description"),
    )


    private val petInstance = petList[petId.toInt()]
    private val _pet = MutableStateFlow(petInstance)
    val pet: StateFlow<Pet> = _pet

    fun onBackPress(){
        navigationViewModel.navigateTo(PopBackStack)
    }

    fun onCancelButtonClick(){
        //
    }
}

class SuccessfulAdoptedScreenViewModelFactory(private val petId: Long, private val navigationViewModel: NavigationViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulAdoptedScreenViewModel::class.java)) {
            return SuccessfulAdoptedScreenViewModel(petId,navigationViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}