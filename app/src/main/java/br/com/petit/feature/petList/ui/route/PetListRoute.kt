package br.com.petit.feature.petList.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NamedNavArgument
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.util.hiltViewModel
import br.com.petit.feature.petList.ui.screen.MainScreen
import br.com.petit.feature.petList.viewmodel.PetListScreenViewModel

class PetListRoute(private val navController: NavController) : UIRoute() {
    override val route = "main"
    override val arguments: List<NamedNavArgument> = listOf()
    @Composable
    override fun content(navBackStackEntry: NavBackStackEntry) {
        val vm: PetListScreenViewModel = hiltViewModel(LocalContext.current, navBackStackEntry)
        MainScreen(navController, vm)
    }
}
