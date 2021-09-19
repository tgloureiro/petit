package br.com.petit.feature.petDetails.ui.route

import androidx.compose.foundation.ExperimentalFoundationApi
import br.com.petit.feature.petDetails.ui.screen.PetDetailsScreenHolder
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.petDetails.viewmodel.DetailsScreenViewModel

@ExperimentalFoundationApi
class PetDetailsRoute(private val navController: NavController) : UIRoute() {
  companion object {
    const val routeRoot = "pet"
    const val argumentName = "petId"
  }
  override val routeName = "$routeRoot/{$argumentName}"
  override val arguments = listOf(navArgument(argumentName) { type = NavType.LongType })
  @Composable
  override fun Content(navBackStackEntry: NavBackStackEntry) {
    navBackStackEntry.savedStateHandle.set(
        argumentName, navBackStackEntry.arguments?.getLong(argumentName))

    val detailsScreenViewModel: DetailsScreenViewModel =
        hiltViewModel(LocalContext.current, navBackStackEntry)

    val petBloc = detailsScreenViewModel.petBloc
    val adoptionBloc = detailsScreenViewModel.adoptionBloc
    PetDetailsScreenHolder(navController = navController, petBloc = petBloc, adoptionBloc = adoptionBloc)
  }
}
