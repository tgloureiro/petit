import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.petDetails.bloc.PetLoaded
import br.com.petit.feature.petDetails.bloc.PetLoading
import br.com.petit.feature.petDetails.bloc.PetNotFound
import br.com.petit.feature.petDetails.bloc.PetState
import br.com.petit.feature.petDetails.ui.component.LoadedPetScreen
import br.com.petit.feature.petDetails.ui.component.PetLoadingScreen
import br.com.petit.feature.petDetails.ui.component.PetNotFoundScreen

@Composable
fun PetDetailsScreen(
    state: State<PetState>,
    adoptionState: State<AdoptionState>,
    onBackPressed: () -> Unit,
    onAdoptPressed: (pet: Pet) -> Unit,
    onCancelAdoptionPressed: () -> Unit,
) {
    when (state.value) {
        is PetLoaded -> {
            val pet = (state.value as PetLoaded).pet
            LoadedPetScreen(
                pet,
                adoptionState,
                onBackPressedCallback = onBackPressed,
                onAdoptClicked = { onAdoptPressed(pet) },
                onCancelAdoptionClicked = onCancelAdoptionPressed)
        }
        is PetLoading -> {
            PetLoadingScreen(onBackPressed)
        }
        PetNotFound -> {
            PetNotFoundScreen(onBackPressed)
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
