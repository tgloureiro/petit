import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.pet.bloc.PetLoaded
import br.com.petit.feature.pet.bloc.PetLoading
import br.com.petit.feature.pet.bloc.PetNotFound
import br.com.petit.feature.pet.bloc.PetState
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.petDetails.ui.component.DetailsLoadedPet

@Composable
fun DetailsScreen(
    state: State<PetState>,
    onBackPressed: () -> Unit,
    onAdoptPressed: (pet: Pet) -> Unit
) {
    when (state.value) {
        is PetLoaded -> {
            val pet = (state.value as PetLoaded).pet
            DetailsLoadedPet(
                pet,
                onBackPressedCallback = onBackPressed,
                onAdoptClicked = { onAdoptPressed(pet) })
        }
        is PetLoading -> {
            Scaffold(
                topBar = {
                    BackAppBar(title = "Loading pet details...", onBackPressed = onBackPressed)
                },
                modifier = Modifier.semantics { testTag = "loading" }) {
                Box(Modifier.fillMaxWidth().fillMaxHeight()) {
                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center).semantics {
                            testTag = "circularProgressIndicator"
                        })
                }
            }
        }
        PetNotFound -> {
            Scaffold(
                topBar = { BackAppBar(title = "Error loading pet") {} },
                modifier = Modifier.semantics { testTag = "petNotFound" }) {
                Text(text = "Pet not found!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun DetailsScreenPreview() {
    PetitTheme() {
        // DetailsScreen(viewModel)
    }
}
