package br.com.petit.ui.screen.details

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import br.com.petit.ui.theme.PetitTheme
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailsLoadedPetTest {

    @get:Rule var hiltRule = HiltAndroidRule(this)

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
        val node = composeTestRule.onNodeWithContentDescription(label = "Petit")
        node.assertExists().performClick()
        Truth.assertThat(backPressedCount).isEqualTo(1)
    }

    // TODO: expand tests
}
