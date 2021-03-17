package br.com.petit.feature.petDetails.ui.screen

import DetailsScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.pet.bloc.PetLoaded
import br.com.petit.feature.pet.bloc.PetLoading
import br.com.petit.feature.pet.bloc.PetNotFound
import br.com.petit.feature.pet.bloc.PetState
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsScreenTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun loading_showProgressIndicator() {
        val stateFlow = MutableStateFlow<PetState>(PetLoading)

        composeTestRule.setContent {
            PetitTheme { DetailsScreen(stateFlow.collectAsState(), {}, {}) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag("loading")).assertIsDisplayed()
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

        composeTestRule.setContent {
            PetitTheme { DetailsScreen(stateFlow.collectAsState(), {}, {}) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag("detailsLoadedPet")).assertIsDisplayed()
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

        composeTestRule.setContent {
            PetitTheme { DetailsScreen(stateFlow.collectAsState(), {}, {}) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag("petNotFound")).assertIsDisplayed()
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

        composeTestRule.setContent {
            PetitTheme { DetailsScreen(stateFlow.collectAsState(), {}, {}) }
        }

        // TODO: Remove hardcoded strings
        stateFlow.tryEmit(PetLoaded(pet))
        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasTestTag("detailsLoadedPet")).assertIsDisplayed()
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

        composeTestRule.setContent {
            PetitTheme { DetailsScreen(stateFlow.collectAsState(), { backCounter++ }, {}) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasTestTag("detailsLoadedPet")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("backButton")).performClick()
        assertThat(backCounter).isEqualTo(1)
    }
}
