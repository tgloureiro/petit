package br.com.petit.feature.petDetails.ui.component

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

class DetailsLoadedPetTest {

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

        var backPressedCount = 0
        val onBackPressed: () -> Unit = { backPressedCount++ }
        var adoptPressedCount = 0
        val onAdoptClicked: () -> Unit = { adoptPressedCount++ }

        composeTestRule.setContent {
            PetitTheme { DetailsLoadedPet(pet, onBackPressed, onAdoptClicked) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNodeWithText("Adopt ${pet.name}").assertExists()
        composeTestRule.onNodeWithText("Adopt ${pet.name}").performClick()
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

        var backPressedCount = 0
        val onBackPressed: () -> Unit = { backPressedCount++ }
        var adoptPressedCount = 0
        val onAdoptClicked: () -> Unit = { adoptPressedCount++ }

        composeTestRule.setContent {
            PetitTheme { DetailsLoadedPet(pet, onBackPressed, onAdoptClicked) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNode(hasTestTag("backButton")).performClick()
        Truth.assertThat(backPressedCount).isEqualTo(1)
    }

    // TODO: expand tests
}
