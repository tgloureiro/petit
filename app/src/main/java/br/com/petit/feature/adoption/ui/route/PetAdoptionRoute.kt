package br.com.petit.feature.adoption.ui.route

import AdoptionScreenHolder
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NamedNavArgument
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.adoption.ui.viewmodel.AdoptionScreenViewModel

class PetAdoptionRoute(private val navController: NavController) : UIRoute() {
  override val routeName = "adoption"
  override val arguments = listOf<NamedNavArgument>()
  @Composable
  override fun Content(navBackStackEntry: NavBackStackEntry) {
    val vm: AdoptionScreenViewModel = hiltViewModel(LocalContext.current, navBackStackEntry)
    val adoptionBloc = vm.adoptionBloc
    AdoptionScreenHolder(navController = navController, adoptionBloc = adoptionBloc)
  }
}
