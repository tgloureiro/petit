package br.com.petit.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import br.com.petit.bloc.Details
import br.com.petit.bloc.Navigator
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import br.com.petit.repository.LocalPetRepository
import br.com.petit.repository.PetRepository
import br.com.petit.repository.StaticPetRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel  @Inject constructor(
        private val petRepository: LocalPetRepository,
        private val navigator: Navigator
) :
    ViewModel() {
    val pets: Flow<List<Pet>> = petRepository.fetchPets()

    fun onPetSelected(petId:Long){
        navigator.navigateTo(Details(petId))
    }

}

class MainScreenViewModelFactory(private val repository: LocalPetRepository, private val navigator: Navigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            return MainScreenViewModel(repository,navigator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}