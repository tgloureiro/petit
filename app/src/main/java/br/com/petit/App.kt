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
            val vm : MainScreenViewModel = viewModel(
                factory = HiltViewModelFactory(
                    LocalContext.current, it
                )
            )
            MainScreen(navController,vm)
        }
        composable("details/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.LongType })) {
            it.savedStateHandle.set("petId",it.arguments?.getLong("petId"))

            val vm : DetailsScreenViewModel = viewModel(
                factory = HiltViewModelFactory(
                    LocalContext.current, it
                )
            )
            DetailsScreen(navController,vm)
        }
        composable("adoption/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.LongType })) {
            it.savedStateHandle.set("petId",it.arguments?.getLong("petId"))

            val vm : AdoptionScreenViewModel = viewModel(
                factory = HiltViewModelFactory(
                    LocalContext.current, it
                )
            )
            AdoptionScreen(navController,vm)
        }
    }


    /*val routeState = navigator.state.collectAsState()
    val route = routeState.value

    val activity = LocalContext.current as ComponentActivity

    PetitTheme {
        when (route) {
            is MainRoute -> {
                val mainVM = ViewModelProvider(activity).get(MainScreenViewModel::class.java)
                MainScreen(mainVM)
            }

            is DetailsRoute -> {
                activity.viewModelStore.clear()
                //ViewModelProviders make a bloc provider for the app
                val detailsVM = viewModel<DetailsScreenViewModel>()
                DetailsScreen(detailsVM)
                /*DetailsScreenViewModel by viewModels {
                    DetailsScreenViewModel.prprovideFactory(plantDetailViewModelFactory, args.plantId)
                }*/

                /*Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF777777),
                    text = "DetailsRoute"
                )*/
                /*val factory = provider.detail()

                val liveBloc = navigator.getLiveBloc(
                    route,
                    factory
                )

                //Escopo de dependencia da tela
                DetailsScreen(liveBloc
                        as Bloc<
                        DetailsScreenState,
                        DetailsScreenEvent>
                )*/
            }

            is SuccessfulAdoptionRoute -> {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF777777),
                    text = "SuccessfulAdoptionRoute"
                )
                /*val petId = route.petId
                val viewModel = SuccessfulAdoptedScreenViewModel(petId, navigator, repository)
                SuccessfulAdoptionScreen(viewModel)*/
            }
        }
    }*/

}
