package br.com.petit.feature.pet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import br.com.petit.feature.pet.bloc.FeelingLuckyBloc
import br.com.petit.feature.pet.bloc.PetListBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetGridScreenViewModel @Inject constructor(
    val petListBloc: PetListBloc,
    val adoptionBloc: AdoptionBloc,
    ) : ViewModel(){
        val feelingLuckyBloc: FeelingLuckyBloc = FeelingLuckyBloc(viewModelScope)
    }
