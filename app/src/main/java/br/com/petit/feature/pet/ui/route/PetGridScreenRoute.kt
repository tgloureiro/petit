package br.com.petit.feature.pet.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navigate
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.pet.bloc.PetListLoaded
import br.com.petit.feature.pet.ui.screen.MainScreen
import br.com.petit.feature.pet.ui.viewmodel.PetGridScreenViewModel
import br.com.petit.feature.petDetails.ui.route.PetDetailsRoute

class PetGridScreenRoute(private val navController: NavController) : UIRoute() {
    override val route = routeRoot
    override val arguments: List<NamedNavArgument> = listOf()

    @Composable
    override fun Content(navBackStackEntry: NavBackStackEntry) {
        val vm: PetGridScreenViewModel = hiltViewModel(LocalContext.current, navBackStackEntry)
        val bloc = vm.petListBloc

        val onNavigateToPetDetails: (Long) -> Unit = { petId ->
            navController.navigate("${PetDetailsRoute.routeRoot}/$petId")
        }

        val onFeelingLucky: () -> Unit = {
            val petListState = bloc.state.value
            if (petListState is PetListLoaded && petListState.pets.isNotEmpty()) {
                val petId = petListState.pets.random().id
                navController.navigate("${PetDetailsRoute.routeRoot}/$petId")
            }
        }

        MainScreen(bloc.state.collectAsState(), onNavigateToPetDetails, onFeelingLucky)
    }

    companion object {
        const val routeRoot = "main"
    }
}
