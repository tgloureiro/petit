package br.com.petit.feature.petDetails.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.NoAdoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

class LoadedPetScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testAdoptClickButton() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var adoptPressedCount = 0
        val onAdoptClicked: () -> Unit = { adoptPressedCount++ }

        var adoptButtonText = ""
        composeTestRule.setContent {
            PetitTheme {
                adoptButtonText = stringResource(id = R.string.adopt_name, pet.name)
                LoadedPetScreen(pet, adoptionState, {}, onAdoptClicked, {})
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(adoptButtonText).assertExists()
        composeTestRule.onNodeWithText(adoptButtonText).performClick()
        Truth.assertThat(adoptPressedCount).isEqualTo(1)
    }

    @Test
    fun testBackClickButton() {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        val adoptionState = mutableStateOf<AdoptionState>(NoAdoption)

        var backPressedCount = 0
        val onBackPressed: () -> Unit = { backPressedCount++ }

        // TODO: How to get a string resource in a cleaner way?
        var backButtonTestTag = ""
        composeTestRule.setContent {
            PetitTheme {
                backButtonTestTag = stringResource(id = R.string.back_button_test_tag)
                LoadedPetScreen(pet, adoptionState, onBackPressed, {}, {})
            }
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNode(hasTestTag(backButtonTestTag)).performClick()
        Truth.assertThat(backPressedCount).isEqualTo(1)
    }

    // TODO: expand test coverage
}
