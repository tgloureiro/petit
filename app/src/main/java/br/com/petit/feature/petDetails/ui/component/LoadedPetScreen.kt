package br.com.petit.feature.petDetails.ui.component

import BackAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.NoAdoption
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@Composable
fun LoadedPetScreen(
    pet: Pet,
    adoptionState: State<AdoptionState>,
    onBackPressedCallback: () -> Unit,
    onAdoptClicked: () -> Unit,
    onCancelAdoptionClicked: () -> Unit
) {
    val loadedPetScreenTestTag = stringResource(id = R.string.pet_loaded_screen_test_tag)

    Scaffold(
        topBar = {
            BackAppBar(
                title =
                    when (pet.petGender) {
                        PetGender.MALE -> stringResource(R.string.about_male_pet)
                        PetGender.FEMALE -> stringResource(R.string.about_female_pet)
                    }) { onBackPressedCallback() }
        },
        bottomBar = {
            if (adoptionState.value is ValidAdoption &&
                ((adoptionState.value as ValidAdoption).adoption.pet?.id == pet.id)) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().background(color = Color(0xDDFFFFFF))) {
                    Button(
                        onClick = { onCancelAdoptionClicked() },
                        modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.cancel_adoption),
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 20.sp)
                    }
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().background(color = Color(0xDDFFFFFF))) {
                    Button(
                        onClick = { onAdoptClicked() },
                        modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                        Text(
                            // Interpolate string resource with pet's name
                            text =
                                stringResource(
                                    R.string.adopt_name, pet.name.capitalize(Locale.getDefault())),
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 20.sp)
                    }
                }
            }
        },
        modifier = Modifier.semantics { testTag = loadedPetScreenTestTag }) {
        Column(
            modifier =
                Modifier.padding(bottom = 128.dp, start = 24.dp, end = 24.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.height(196.dp).padding(vertical = 12.dp)) {
                CoilImage(
                    data = pet.pictureUrl,
                    "Pet photo",
                    modifier =
                        Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            .aspectRatio(1.305f)
                            .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(Modifier.matchParentSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                )
                Column() {
                    Text(
                        modifier =
                            Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
                        text = pet.name,
                        fontSize = 36.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold)
                    Text(
                        modifier =
                            Modifier.padding(
                                start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
                        fontSize = 16.sp,
                        maxLines = 1,
                        color = Color(0xFF777777),
                        text = pet.petGender.name.capitalize(Locale.getDefault()))
                }
            }

            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
                fontSize = 16.sp,
                color = Color(0xFF777777),
                text =
                    "${pet.name} loves a run, so if you’re looking for a jogging companion or someone to take long walks with, then ${pet.name} is your ${
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
                    } best. Be a hero for ${pet.name}!")
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun DetailsLoadedPetPreview() {
    val pet =
        Pet(
            0,
            "Rudy",
            "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
            PetGender.MALE,
            "Description")

    val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)
    PetitTheme() { LoadedPetScreen(pet, adoptionState, {}, {}, {}) }
}
