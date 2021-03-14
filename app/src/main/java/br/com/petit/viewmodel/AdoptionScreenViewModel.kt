package br.com.petit.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.petit.bloc.NoPetId
import br.com.petit.bloc.PetBloc
import br.com.petit.bloc.PetListBloc
import br.com.petit.bloc.ValidPetId
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import br.com.petit.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.tiagoloureiro.navigator.NavigatorBloc
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class AdoptionScreenViewModel @Inject constructor(
    petListBloc: PetListBloc,
    val navigator: NavigatorBloc,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), LifecycleObserver {

    val petBloc: PetBloc

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
