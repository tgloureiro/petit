package br.com.petit.core.bloc.feature.pet.bloc

import br.com.petit.core.bloc.MainCoroutineRule
import br.com.petit.feature.pet.bloc.PetListBloc
import br.com.petit.feature.pet.bloc.PetListLoaded
import br.com.petit.feature.pet.bloc.PetListLoading
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import br.com.petit.feature.pet.repository.PetRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
internal class PetListBlocTest {

    private val testPet =
        Pet(
            id = 0,
            name = "Ruby",
            pictureUrl = "abc",
            petGender = PetGender.FEMALE,
            description = null)

    @ExperimentalCoroutinesApi @get:Rule var mainCoroutineRule = MainCoroutineRule()
    private val repository: PetRepository = Mockito.mock(PetRepository::class.java)

    @Test
    fun initialStateIsLoading() = runBlockingTest {
        val petListFlow = MutableSharedFlow<List<Pet>>()
        `when`(repository.fetchPets()).thenReturn(petListFlow)
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)
        val petListBloc = PetListBloc(repository, scope)
        Truth.assertThat(petListBloc.state.value).isEqualTo(PetListLoading)
    }

    @Test
    fun loading_thenChangeToLoaded() {
        val listOfPets = listOf(testPet)
        val petListFlow = MutableStateFlow(listOfPets)
        `when`(repository.fetchPets()).thenReturn(petListFlow)
        val scope = CoroutineScope(mainCoroutineRule.dispatcher)

        mainCoroutineRule.dispatcher.runBlockingTest {
            val petListBloc = PetListBloc(repository, scope)
            Truth.assertThat(petListBloc.state.value).isEqualTo(PetListLoading)
            advanceTimeBy(3000)
            Truth.assertThat(petListBloc.state.value).isEqualTo(PetListLoaded(listOfPets))
        }
    }
}
