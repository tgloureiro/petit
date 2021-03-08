import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import br.com.petit.ui.screen.MainScreen
import br.com.petit.ui.theme.PetitTheme
import br.com.petit.viewmodel.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Level
import java.util.logging.Logger

@Composable
@ExperimentalFoundationApi
fun App() {
    val coroutineScope = rememberCoroutineScope()

    val navViewModel : NavigationViewModel = viewModel()
    val navController = rememberNavController()

    coroutineScope.launch {
            navViewModel.route.onEach { navigationRoute ->
                when (navigationRoute) {
                    is Details -> {
                        val petIdStr = navigationRoute.petId.toString()
                        Logger.getLogger("App").log(Level.WARNING,petIdStr)
                        navController.navigate("detail/$petIdStr")
                    }
                    Main -> navController.navigate("main")
                    is SuccessfulAdoption -> navController.navigate("success/${navigationRoute.petId}")
                    PopBackStack -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }

    PetitTheme {
        NavHost(navController, startDestination = "main") {
            composable("main") { MainScreen(navController) }
            composable("detail/{petId}",
                arguments = listOf(navArgument("petId") { type = NavType.LongType }))
            { backStackEntry ->
                val petId = backStackEntry.arguments?.getLong("petId")

                if(petId!=null) {
                    val viewModelFactory = DetailsScreenViewModelFactory(petId,navViewModel)
                    val viewModel: DetailsScreenViewModel = viewModel(factory = viewModelFactory)
                    DetailsScreen(viewModel)
                }else{
                    //TODO: Pet not found page
                }
            }
            composable("success/{petId}",
                arguments = listOf(navArgument("petId") { type = NavType.LongType }))
            { backStackEntry ->
                val petId = backStackEntry.arguments?.getLong("petId")

                if(petId!=null) {
                    val viewModelFactory = SuccessfulAdoptedScreenViewModelFactory(petId, navViewModel)
                    val viewModel: SuccessfulAdoptedScreenViewModel = viewModel(factory = viewModelFactory)
                    SuccessfulAdoptionScreen(viewModel)
                }else{
                    //TODO: Pet not found page
                }
            }

        }
    }

}
