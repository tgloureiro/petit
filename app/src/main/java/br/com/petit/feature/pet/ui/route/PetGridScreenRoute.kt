package br.com.petit.feature.pet.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navigate
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.pet.bloc.Next
import br.com.petit.feature.pet.bloc.RandomPetId
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.ui.screen.MainScreen
import br.com.petit.feature.pet.ui.screen.PetGridScreenHolder
import br.com.petit.feature.pet.ui.viewmodel.PetGridScreenViewModel
import br.com.petit.feature.petDetails.ui.route.PetDetailsRoute
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
