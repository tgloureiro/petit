package br.com.petit.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.petit.bloc.PetBloc
import br.com.petit.bloc.PetListBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel
@Inject
constructor(
    petListBloc: PetListBloc,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), LifecycleObserver {
    val petBloc: PetBloc = PetBloc(savedStateHandle["petId"], petListBloc, viewModelScope)
}
