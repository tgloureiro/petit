package br.com.petit.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.petit.bloc.NoPetId
import br.com.petit.bloc.PetBloc
import br.com.petit.bloc.PetListBloc
import br.com.petit.bloc.ValidPetId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    petListBloc: PetListBloc,
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel(), LifecycleObserver {
    val petBloc : PetBloc

    init{
        val petId : Long? = savedStateHandle["petId"]
        val petIdArg = if(petId != null){
            ValidPetId(petId)
        }else{
            NoPetId
        }

        petBloc = PetBloc(
            petIdArg,
            petListBloc,
            viewModelScope
        )
    }


}
