package br.com.petit.feature.petDetails.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.adoption.model.Adoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
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
    val pet =
        Pet(
            0,
            "Rudy",
            "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
            PetGender.MALE,
            "Description")

    val petState = mutableStateOf<PetState>(PetLoaded(
        pet
    ))

    val adoptionState = mutableStateOf<AdoptionState>(
        ValidAdoption(
            Adoption(0, pet)
        ))

    PetitTheme {
        PetDetailsScreen(
            petState,
            adoptionState,
            {},{},{}
        )
    }
}
