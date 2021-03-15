package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import br.com.petit.bloc.PetListBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel  @Inject constructor(
    val petListBloc: PetListBloc
) : ViewModel()