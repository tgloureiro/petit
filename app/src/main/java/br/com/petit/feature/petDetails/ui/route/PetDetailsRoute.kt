package br.com.petit.feature.petDetails.ui.route

import DetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.adoption.bloc.Adopt
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.petDetails.viewmodel.DetailsScreenViewModel

class PetDetailsRoute(private val navController: NavController) : UIRoute() {
    companion object {
        const val routeRoot = "pet"
        const val argumentName = "petId"
    }
    override val route = "$routeRoot/{$argumentName}"
    override val arguments = listOf(navArgument(argumentName) { type = NavType.LongType })
    @Composable
    override fun content(navBackStackEntry: NavBackStackEntry) {
        navBackStackEntry.savedStateHandle.set(
            argumentName, navBackStackEntry.arguments?.getLong(argumentName))

        val detailsScreenViewModel: DetailsScreenViewModel =
            hiltViewModel(LocalContext.current, navBackStackEntry)

        val petBloc = detailsScreenViewModel.petBloc
        val adoptionBloc = detailsScreenViewModel.adoptionBloc
        val state = petBloc.state.collectAsState()
        val onBackPressed: () -> Unit = { navController.popBackStack() }
        val onAdoptPressed: (Pet) -> Unit = { pet ->
            adoptionBloc.add(Adopt(pet))
            navController.navigate("adoption")
        }

        DetailsScreen(state, onBackPressed, onAdoptPressed)
    }
}
