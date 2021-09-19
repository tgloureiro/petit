package br.com.petit.feature.pet.ui.route

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.pet.ui.screen.PetGridScreenHolder
import br.com.petit.feature.pet.ui.viewmodel.PetGridScreenViewModel

@ExperimentalFoundationApi
class PetGridScreenRoute(private val navController: NavController) : UIRoute() {
  override val routeName = routeRoot
  override val arguments: List<NamedNavArgument> = listOf()

  @Composable
  override fun Content(navBackStackEntry: NavBackStackEntry) {
    val vm: PetGridScreenViewModel = hiltViewModel(LocalContext.current, navBackStackEntry)

    val bloc = vm.petListBloc
    val feelingLuckyBloc = vm.feelingLuckyBloc
    val adoptionBloc = vm.adoptionBloc


    PetGridScreenHolder(
      navController,
      bloc,
      feelingLuckyBloc,
      adoptionBloc
    )
  }

  companion object {
    const val routeRoot = "main"
  }
}
