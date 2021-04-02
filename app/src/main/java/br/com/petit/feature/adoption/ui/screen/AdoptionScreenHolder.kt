import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import br.com.petit.feature.adoption.bloc.AdoptionBloc
import br.com.petit.feature.adoption.bloc.CancelAdoption

@Composable
fun AdoptionScreenHolder(
    navController: NavController,
    adoptionBloc: AdoptionBloc
) {
    val onCancelPressed : () -> Unit = {
        adoptionBloc.add(CancelAdoption)
        navController.popBackStack()
    }

    val onBackPressed : () -> Unit = {
        navController.popBackStack()
    }

    AdoptionScreen(
        adoptionBloc.state.collectAsState(),
        onCancelPressed = onCancelPressed,
        onBackPressed = onBackPressed)
}
