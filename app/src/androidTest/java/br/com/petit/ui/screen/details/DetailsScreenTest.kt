package br.com.petit.ui.screen.details

import DetailsScreen
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import br.com.petit.bloc.PetBloc
import br.com.petit.bloc.PetListBloc
import br.com.petit.bloc.PetLoading
import br.com.petit.bloc.PetState
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import br.com.petit.ui.theme.PetitTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
class DetailsScreenTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule var hiltRule = HiltAndroidRule(this)

    @get:Rule val composeTestRule = createComposeRule()

    @Inject lateinit var petListBloc: PetListBloc

    @Test
    fun loading_showProgressIndicator() {
        val stateFlow = MutableStateFlow<PetState>(PetLoading)

        val petBloc = mock(PetBloc::class.java)

        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        composeTestRule.setContent {
            val navController = rememberNavController()
            PetitTheme { DetailsScreen(navController, petBloc) }
        }

        // TODO: Remove hardcoded strings
        composeTestRule.onNodeWithText("Loading pet details...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fail").assertIsDisplayed()

        // composeTestRule.onNodeWithText("Adopt ${pet.name}").performClick()
        // Truth.assertThat(adoptPressedCount).isEqualTo(1)
    }

    // TODO: expand tests
}
