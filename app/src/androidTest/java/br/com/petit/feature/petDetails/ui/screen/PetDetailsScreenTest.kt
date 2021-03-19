package br.com.petit.feature.petDetails.ui.screen

import PetDetailsScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.NoAdoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import br.com.petit.feature.petDetails.bloc.PetLoaded
import br.com.petit.feature.petDetails.bloc.PetLoading
import br.com.petit.feature.petDetails.bloc.PetNotFound
import br.com.petit.feature.petDetails.bloc.PetState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PetDetailsScreenTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun loading_showProgressIndicator() {
        val stateFlow = MutableStateFlow<PetState>(PetLoading)
        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var petLoadingScreenTestTag = ""
        composeTestRule.setContent {
            PetitTheme {
                petLoadingScreenTestTag = stringResource(id = R.string.pet_loading_screen_test_tag)
                PetDetailsScreen(stateFlow.collectAsState(), adoptionState, {}, {}, {})
            }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag(petLoadingScreenTestTag)).assertIsDisplayed()
    }

    @Test
    fun loaded_showDetailsPetLoaded() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val stateFlow = MutableStateFlow<PetState>(PetLoaded(pet))
        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var loadedPetScreenTestTag = ""

        composeTestRule.setContent {
            PetitTheme {
                loadedPetScreenTestTag = stringResource(id = R.string.pet_loaded_screen_test_tag)
                PetDetailsScreen(stateFlow.collectAsState(), adoptionState, {}, {}, {})
            }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag(loadedPetScreenTestTag)).assertIsDisplayed()
    }

    @Test
    fun notFound_showPetNotFound() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val stateFlow = MutableStateFlow<PetState>(PetNotFound)

        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var petNotFoundScreenTestTag = ""
        composeTestRule.setContent {
            PetitTheme {
                petNotFoundScreenTestTag =
                    stringResource(id = R.string.pet_not_found_screen_test_tag)
                PetDetailsScreen(stateFlow.collectAsState(), adoptionState, {}, {}, {})
            }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag(petNotFoundScreenTestTag)).assertIsDisplayed()
    }

    @Test
    fun loading_changedToLoaded() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val stateFlow = MutableStateFlow<PetState>(PetLoading)

        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var loadedPetScreenTestTag = ""

        composeTestRule.setContent {
            PetitTheme {
                loadedPetScreenTestTag = stringResource(id = R.string.pet_loaded_screen_test_tag)
                PetDetailsScreen(stateFlow.collectAsState(), adoptionState, {}, {}, {})
            }
        }

        // TODO: Remove hardcoded strings
        stateFlow.tryEmit(PetLoaded(pet))
        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasTestTag(loadedPetScreenTestTag)).assertIsDisplayed()
    }
    // TODO: expand tests

    @Test
    fun loaded_onBack() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val stateFlow = MutableStateFlow<PetState>(PetLoaded(pet))
        var backCounter = 0

        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var loadedPetScreenTestTag = ""
        var backButtonTestTag = ""

        composeTestRule.setContent {
            PetitTheme {
                backButtonTestTag = stringResource(id = R.string.back_button_test_tag)
                loadedPetScreenTestTag = stringResource(id = R.string.pet_loaded_screen_test_tag)
                PetDetailsScreen(
                    stateFlow.collectAsState(), adoptionState, { backCounter++ }, {}, {})
            }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasTestTag(loadedPetScreenTestTag)).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag(backButtonTestTag)).performClick()
        assertThat(backCounter).isEqualTo(1)
    }
}
