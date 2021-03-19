package br.com.petit.feature.pet.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.util.ListDensity
import br.com.petit.core.ui.util.stateSaver
import br.com.petit.feature.pet.bloc.PetListLoaded
import br.com.petit.feature.pet.bloc.PetListLoading
import br.com.petit.feature.pet.bloc.PetListState
import br.com.petit.feature.pet.ui.component.PetListLoadedScreen
import br.com.petit.feature.pet.ui.component.PetListLoadingScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    petListState: State<PetListState>,
    navigateToPetDetails: (Long) -> Unit,
    onFeelingLucky: () -> Unit
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
            PetListLoadedScreen(
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
