import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionLoading
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.NoAdoption
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.adoption.model.Adoption
import br.com.petit.feature.adoption.ui.component.LoadingAdoptionScreen
import br.com.petit.feature.adoption.ui.component.NoAdoptionScreen
import br.com.petit.feature.adoption.ui.component.ValidAdoptionScreen
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender

@Composable
fun AdoptionScreen(
    state: State<AdoptionState>,
    onCancelPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    when (state.value) {
        is ValidAdoption -> {
            val pet = (state.value as ValidAdoption).adoption.pet
            ValidAdoptionScreen(
                pet = pet, onBackPressed = onBackPressed, onCancelPressed = onCancelPressed)
        }
        AdoptionLoading -> {
            LoadingAdoptionScreen(onBackPressed)
        }
        NoAdoption -> {
            NoAdoptionScreen(onBackPressed)
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun AdoptionScreenPreview() {
    val pet =
        Pet(
            0,
            "Rudy",
            "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
            PetGender.MALE,
            "Description")

    val loadingState = mutableStateOf<AdoptionState>(AdoptionLoading)
    val noAdoptionState = mutableStateOf<AdoptionState>(NoAdoption)
    val validAdoptionState = mutableStateOf<AdoptionState>(ValidAdoption(Adoption(0, pet)))
    PetitTheme { AdoptionScreen(validAdoptionState, {}, {}) }
}
