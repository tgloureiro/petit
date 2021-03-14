package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import br.com.petit.bloc.PetListBloc
import br.com.petit.navigator.DetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.tiagoloureiro.navigator.Navigate
import tech.tiagoloureiro.navigator.NavigatorBloc
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel  @Inject constructor(
    val petListBloc: PetListBloc
) : ViewModel() {

    init{
        Logger.getLogger("MainScreenViewModel").log(
            Level.INFO, "MainScreenViewModel created")
    }


    override fun onCleared() {
        super.onCleared()
        Logger.getLogger("MainScreenViewModel").log(
            Level.INFO, "MainScreenViewModel cleared!!!")
    }
}

/*
class MainViewModelFactory(private val petRepository: PetRepository,
                           private val navigator: NavigatorBloc
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainScreenViewModel(petRepository,navigator) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}*/