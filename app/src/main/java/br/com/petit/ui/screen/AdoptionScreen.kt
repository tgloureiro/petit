import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.petit.R
import br.com.petit.bloc.PetLoaded
import br.com.petit.bloc.PetLoading
import br.com.petit.bloc.PetNotFound
import br.com.petit.ui.theme.PetitTheme
import br.com.petit.viewmodel.AdoptionScreenViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AdoptionScreen(navController: NavController, viewModel: AdoptionScreenViewModel) {

    val state = viewModel.petBloc.state.collectAsState()
    when (state.value) {
        is PetLoaded -> {
            val pet = (state.value as PetLoaded ).pet
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Image(
                                painter = painterResource(R.drawable.ic_logo_app_bar),
                                contentDescription = "menu",
                            )
                        },
                        backgroundColor = MaterialTheme.colors.background)
                }) {
                Column(modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
                    // title
                    Text(
                        modifier =
                        Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                        text = "Congratulations! You adopted ${pet!!.name}",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                    // img/
                    CoilImage(
                        data = pet!!.pictureUrl,
                        "Grid",
                        modifier =
                        Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            .aspectRatio(1.305f)
                            .widthIn(max = 128.dp)
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = true),
                        contentScale = ContentScale.Crop)

                    Image(
                        painterResource(R.drawable.map),
                        "Map",
                        modifier =
                        Modifier.padding(horizontal = 46.dp, vertical = 24.dp)
                            .aspectRatio(1.69f)
                            .fillMaxWidth()
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = true),
                        contentScale = ContentScale.Crop)

                    Text(
                        text =
                        "Your new buddy is being comfortably delivered to your house right now. ",
                        modifier = Modifier.padding(horizontal = 36.dp),
                        textAlign = TextAlign.Center)
                    // footer button
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier =
                        Modifier.padding(horizontal = 24.dp, vertical = 36.dp).fillMaxWidth(),
                        colors =
                        ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.background)) {
                        Text(
                            text = "CANCEL",
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 46.dp),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
        PetLoading -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Adopted Pet: Error") },
                        backgroundColor = MaterialTheme.colors.background)
                }) { Text("Error: adopted pet not loaded.") }
        }
        PetNotFound -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Adopted Pet: Error") },
                        backgroundColor = MaterialTheme.colors.background)
                }) { Text("Error: adopted pet not found.") }
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun SuccessfulAdoptionScreenPreview() {
    val viewModel: AdoptionScreenViewModel = viewModel()

    PetitTheme() { AdoptionScreen(rememberNavController(), viewModel) }
}
