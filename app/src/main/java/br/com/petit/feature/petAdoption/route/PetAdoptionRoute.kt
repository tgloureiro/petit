package br.com.petit.feature.petAdoption.route

import AdoptionScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NamedNavArgument
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.adoption.bloc.CancelAdoption
import br.com.petit.feature.petAdoption.viewmodel.AdoptionScreenViewModel

class PetAdoptionRoute(private val navController: NavController) : UIRoute() {
    override val route = "adoption"
    override val arguments = listOf<NamedNavArgument>()
    @Composable
    override fun content(navBackStackEntry: NavBackStackEntry) {
        val vm: AdoptionScreenViewModel = hiltViewModel(LocalContext.current, navBackStackEntry)
        val adoptionBloc = vm.adoptionBloc
        AdoptionScreen(
            adoptionBloc.state.collectAsState(),
            onCancelPressed = {
                adoptionBloc.add(CancelAdoption)
                navController.popBackStack()
            })
    }
}
