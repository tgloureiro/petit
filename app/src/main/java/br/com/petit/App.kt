import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.petit.ui.screen.MainScreen
import br.com.petit.viewmodel.AdoptionScreenViewModel
import br.com.petit.viewmodel.DetailsScreenViewModel
import br.com.petit.viewmodel.MainScreenViewModel

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            val vm: MainScreenViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, it))
            MainScreen(navController, vm)
        }
        composable(
            "details/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.LongType })) {
            it.savedStateHandle.set("petId", it.arguments?.getLong("petId"))

            val vm: DetailsScreenViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, it))
            DetailsScreen(navController, vm.petBloc)
        }
        composable(
            "adoption/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.LongType })) {
            it.savedStateHandle.set("petId", it.arguments?.getLong("petId"))

            val vm: AdoptionScreenViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, it))
            AdoptionScreen(navController, vm)
        }
    }
}
