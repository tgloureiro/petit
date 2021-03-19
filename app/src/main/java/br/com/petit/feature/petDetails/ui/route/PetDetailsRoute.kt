package br.com.petit.feature.petDetails.ui.route

import PetDetailsScreen
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.adoption.bloc.Adopt
import br.com.petit.feature.adoption.bloc.CancelAdoption
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.ui.route.PetGridScreenRoute
import br.com.petit.feature.petDetails.viewmodel.DetailsScreenViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PetDetailsRoute(private val navController: NavController) : UIRoute() {
    companion object {
        const val routeRoot = "pet"
        const val argumentName = "petId"
    }
    override val route = "$routeRoot/{$argumentName}"
    override val arguments = listOf(navArgument(argumentName) { type = NavType.LongType })
    @Composable
    override fun Content(navBackStackEntry: NavBackStackEntry) {
        navBackStackEntry.savedStateHandle.set(
            argumentName, navBackStackEntry.arguments?.getLong(argumentName))

        val detailsScreenViewModel: DetailsScreenViewModel =
            hiltViewModel(LocalContext.current, navBackStackEntry)

        val petBloc = detailsScreenViewModel.petBloc
        val adoptionBloc = detailsScreenViewModel.adoptionBloc
        val state = petBloc.state.collectAsState()
        val adoptionState = adoptionBloc.state.collectAsState()

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(coroutineScope) {
            val navJob =
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

        PetDetailsScreen(
            state, adoptionState, onBackPressed, onAdoptPressed, onCancelAdoptionPressed)
    }
}
