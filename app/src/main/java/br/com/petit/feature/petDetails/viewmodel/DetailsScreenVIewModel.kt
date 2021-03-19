package br.com.petit.feature.petDetails.viewmodel

import androidx.lifecycle.*
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import br.com.petit.feature.pet.bloc.PetListBloc
import br.com.petit.feature.petDetails.bloc.PetBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel
@Inject
constructor(
    petListBloc: PetListBloc,
    val adoptionBloc: AdoptionBloc,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), LifecycleObserver {
    val petBloc: PetBloc = PetBloc(savedStateHandle["petId"], petListBloc, viewModelScope)
}
