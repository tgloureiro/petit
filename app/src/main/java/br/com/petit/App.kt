import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.petit.bloc.*
import br.com.petit.navigator.*
import br.com.petit.provider.BlocProvider
import br.com.petit.repository.PetRepository
import br.com.petit.ui.screen.MainScreen
import br.com.petit.ui.theme.PetitTheme
import br.com.petit.viewmodel.MainScreenViewModel
import br.com.petit.viewmodel.SuccessfulAdoptedScreenViewModel
import tech.tiagoloureiro.navigator.NavigatorBloc
import java.util.*


@Composable
fun App(navigator: NavigatorBloc) {
    val routeState = navigator.state.collectAsState()
    val route = routeState.value

    val activity = LocalContext.current as ComponentActivity

    PetitTheme {
        when (route) {
            is MainRoute -> {

                val mainVM = viewModel<MainScreenViewModel>()
                MainScreen(mainVM)
            }

            is DetailsRoute -> {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF777777),
                    text = "DetailsRoute"
                )
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
    }

}
