package br.com.petit.feature.pet.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import br.com.petit.feature.pet.bloc.FeelingLuckyBloc
import br.com.petit.feature.pet.bloc.Next
import br.com.petit.feature.pet.bloc.PetListBloc
import br.com.petit.feature.pet.bloc.RandomPetId
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.petDetails.ui.route.PetDetailsRoute
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetGridScreenHolder(
    navController: NavController,
    petListBloc: PetListBloc,
    feelingLuckyBloc: FeelingLuckyBloc,
    adoptionBloc: AdoptionBloc
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        feelingLuckyBloc
            .transition
            .onEach { transition ->
                val state = transition.newState
                if (state is RandomPetId) {
                    val petId = state.petId
                    navController.navigate("${PetDetailsRoute.routeRoot}/$petId")
                }
            }
            .launchIn(this)
    }

    val onNavigateToPetDetails: (Long) -> Unit = { petId ->
        navController.navigate("${PetDetailsRoute.routeRoot}/$petId")
    }

    val onFeelingLucky: (pets: List<Pet>) -> Unit = { pets -> feelingLuckyBloc.add(Next(pets)) }

    MainScreen(
        petListBloc.state.collectAsState(),
        adoptionBloc.state.collectAsState(),
        onNavigateToPetDetails, onFeelingLucky
    )
}
