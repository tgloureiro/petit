import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import br.com.petit.bloc.PetLoaded
import br.com.petit.bloc.PetLoading
import br.com.petit.bloc.PetNotFound
import br.com.petit.model.PetGender
import br.com.petit.ui.screen.details.DetailsLoadedPet
import br.com.petit.ui.theme.PetitTheme
import br.com.petit.viewmodel.DetailsScreenViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*


@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsScreenViewModel
){
    val state = viewModel.petBloc.state.collectAsState()

    when(state.value){
        is PetLoaded -> {
            val pet = (state.value as PetLoaded).pet
            DetailsLoadedPet(pet,
                onBackPressedCallback = {
                    navController.popBackStack()
                },
                onAdoptClicked = {
                    navController.navigate("adoption/${pet.id}")
                }
            )
            Scaffold(topBar = {
                BackAppBar(
                    title = when (pet.petGender) {
                        PetGender.MALE -> "About him"
                        PetGender.FEMALE -> "About her"
                    }
                ) {
                    navController.popBackStack()
                }
            }) {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        modifier =
                        Modifier
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState())
                    ) {
                        CoilImage(
                            data = pet.pictureUrl,
                            "Pet photo",
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .aspectRatio(1.305f)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 0.dp,
                                bottom = 0.dp
                            ),
                            text = pet.name,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 0.dp,
                                bottom = 16.dp
                            ),
                            fontSize = 16.sp,
                            color = Color(0xFF777777),
                            text = pet.petGender.name.capitalize(Locale.getDefault())
                        )
                        Text(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 0.dp,
                                bottom = 16.dp
                            ),
                            fontSize = 16.sp,
                            color = Color(0xFF777777),
                            text = "${pet.name} loves a run, so if you’re looking for a jogging companion or someone to take long walks with, then ${pet.name} is your ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "boy"
                                    PetGender.FEMALE -> "lady"
                                }
                            }. ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "He"
                                    PetGender.FEMALE -> "She"
                                }
                            } does need a secure yard with high fences, but ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "his"
                                    PetGender.FEMALE -> "her"
                                }
                            } long legs make ${pet.name} a very good looking ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "boy"
                                    PetGender.FEMALE -> "girl"
                                }
                            }. Everyone comments on how pretty ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "he"
                                    PetGender.FEMALE -> "she"
                                }
                            } is – a real head turner. ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "He's"
                                    PetGender.FEMALE -> "She's"
                                }
                            } also very loyal and will want to spend time with ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "his"
                                    PetGender.FEMALE -> "her"
                                }
                            } family, so someone home for part of the day would suit ${
                                when (pet.petGender) {
                                    PetGender.MALE -> "him"
                                    PetGender.FEMALE -> "her"
                                }
                            } best. Be a hero for ${pet.name}!"
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate("adoption/${pet.id}")
                        },
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Adopt ${pet.name}",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 20.sp

                        )
                    }
                }
            }
        }
        is PetLoading -> {
            Scaffold(topBar = {
                BackAppBar(title = "Loading pet details...") {
                    navController.popBackStack()
                }
            }) {
                Box(Modifier.fillMaxWidth().fillMaxHeight()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
        PetNotFound -> {
            Scaffold(topBar = {
                BackAppBar(title = "Error loading pet") {

                }
            }) {
               Text(
                   text = "Pet not found!"
               )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun DetailsScreenPreview() {
    //val viewModel: DetailsScreenBloc = viewModel()

    PetitTheme() {
        //DetailsScreen(viewModel)
    }
}