package br.com.petit.core.bloc.feature.pet.bloc

import br.com.petit.core.bloc.*
import br.com.petit.feature.pet.bloc.*
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
internal class PetBlocTest {

    @ExperimentalCoroutinesApi @get:Rule var mainCoroutineRule = MainCoroutineRule()

    private val petListBloc: PetListBloc = mock(PetListBloc::class.java)

    private val testPet =
        Pet(
            id = 0,
            name = "Ruby",
            pictureUrl = "abc",
            petGender = PetGender.FEMALE,
            description = null)

    @Test
    fun loading_returnPetLoadingState() = runBlockingTest {
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)
        val petListState = MutableStateFlow<PetListState>(PetListLoading)
        `when`(petListBloc.state).thenReturn(petListState)
        val petId = 0L
        val petBloc = PetBloc(petId, petListBloc, scope)
        assertThat(petBloc.state.value).isEqualTo(PetLoading)
    }

    @Test
    fun emptyList_returnPetNotFoundState() = runBlockingTest {
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)
        val petListState = MutableStateFlow<PetListState>(PetListLoaded(listOf()))
        `when`(petListBloc.state).thenReturn(petListState)
        val petId = 0L
        val petBloc = PetBloc(petId, petListBloc, scope)
        assertThat(petBloc.state.value).isEqualTo(PetNotFound)
    }

    @Test
    fun loaded_returnPetLoadedState() = runBlockingTest {
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)

        val petListState = MutableStateFlow<PetListState>(PetListLoaded(listOf(testPet)))
        `when`(petListBloc.state).thenReturn(petListState)
        val petId = 0L
        val petBloc = PetBloc(petId, petListBloc, scope)
        assertThat(petBloc.state.value).isEqualTo(PetLoaded(testPet))
    }

    @Test
    fun loading_whenChangeToLoadedReturnPetLoadedState() = runBlockingTest {
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)
        val petListState = MutableStateFlow<PetListState>(PetListLoading)
        `when`(petListBloc.state).thenReturn(petListState)
        val petId = 0L
        val petBloc = PetBloc(petId, petListBloc, scope)
        assertThat(petBloc.state.value).isEqualTo(PetLoading)

        petListState.emit(PetListLoaded(listOf(testPet)))
        assertThat(petBloc.state.value).isEqualTo(PetLoaded(testPet))
    }

    @Test
    fun loading_whenPetDoesntExistsReturnPetNotFoundState() = runBlockingTest {
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)
        val petListState = MutableStateFlow<PetListState>(PetListLoaded(listOf(testPet)))
        `when`(petListBloc.state).thenReturn(petListState)
        val petId = 10000L
        val petBloc = PetBloc(petId, petListBloc, scope)
        assertThat(petBloc.state.value).isEqualTo(PetNotFound)
    }
}
