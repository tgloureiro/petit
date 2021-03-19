package br.com.petit.feature.adoption.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdoptionScreenViewModel
@Inject
constructor(
    val adoptionBloc: AdoptionBloc,
) : ViewModel(), LifecycleObserver
