package br.com.petit.feature.pet.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.util.ListDensity
import br.com.petit.core.ui.util.stateSaver
import br.com.petit.feature.adoption.bloc.*
import br.com.petit.feature.pet.bloc.PetListLoaded
import br.com.petit.feature.pet.bloc.PetListLoading
import br.com.petit.feature.pet.bloc.PetListState
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.ui.component.PetListLoadedScreen
import br.com.petit.feature.pet.ui.component.PetListLoadingScreen

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    petListState: State<PetListState>,
    adoptionState: State<AdoptionState>,
    navigateToPetDetails: (Long) -> Unit,
    onFeelingLucky: (pets: List<Pet>) -> Unit
) {
    var densitySelector by rememberSaveable(saver = stateSaver()) {
        mutableStateOf(ListDensity.TWO)
    }

    val onListDensityChanged: (ListDensity) -> Unit = { listDensity ->
        densitySelector = listDensity
    }

    when (petListState.value) {
        is PetListLoaded -> {
            val pets = (petListState.value as PetListLoaded).pets
            PetListLoadedScreen( adoptionState.value,
                pets, densitySelector, onListDensityChanged, navigateToPetDetails, onFeelingLucky)
        }
        PetListLoading -> {
            PetListLoadingScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun MainScreenPreview() {
    /*val viewModel: PetGridScreenViewModel = viewModel()

    PetitTheme() { MainScreen(rememberNavController(), viewModel) }*/
}
