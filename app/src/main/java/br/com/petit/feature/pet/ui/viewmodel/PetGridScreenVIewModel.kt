package br.com.petit.feature.pet.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.petit.feature.pet.bloc.PetListBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetGridScreenViewModel @Inject constructor(val petListBloc: PetListBloc) : ViewModel()
