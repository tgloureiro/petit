package br.com.petit.feature.petDetails.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import br.com.petit.feature.adoption.bloc.Adopt
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import br.com.petit.feature.adoption.bloc.CancelAdoption
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.ui.route.PetGridScreenRoute
import br.com.petit.feature.petDetails.bloc.PetBloc
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalFoundationApi
@Composable
fun PetDetailsScreenHolder(
    navController: NavController,
    petBloc: PetBloc,
    adoptionBloc: AdoptionBloc,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
            adoptionBloc
                .transition
                .onEach {
                    if (it.newState is ValidAdoption) {
                        navController.navigate("adoption") {
                            popUpTo(PetGridScreenRoute.routeRoot) { inclusive = false }
                        }
                    }
                }
                .launchIn(coroutineScope)
    }

    val onBackPressed: () -> Unit = { navController.popBackStack() }
    val onAdoptPressed: (Pet) -> Unit = { pet -> adoptionBloc.add(Adopt(pet)) }
    val onCancelAdoptionPressed: () -> Unit = { adoptionBloc.add(CancelAdoption) }

    val state = petBloc.state.collectAsState()
    val adoptionState = adoptionBloc.state.collectAsState()

    PetDetailsScreen(
        state, adoptionState, onBackPressed, onAdoptPressed, onCancelAdoptionPressed)
}
