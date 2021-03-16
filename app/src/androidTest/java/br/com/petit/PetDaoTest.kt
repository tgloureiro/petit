package br.com.petit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import br.com.petit.model.PetGender
import br.com.petit.repository.PetDao
import br.com.petit.repository.PetDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class PetDaoTest {

    @get:Rule var mainCoroutineRule = MainCoroutineRule()

    @get:Rule var hiltRule = HiltAndroidRule(this)

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject @Named("test_db") lateinit var database: PetDatabase
    private lateinit var petDao: PetDao

    @Before
    fun setup() {
        hiltRule.inject()
        petDao = database.petDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertPet() = runBlockingTest {
        val pet =
            Pet(
                id = 30000,
                name = "Ruby",
                pictureUrl = "",
                petGender = PetGender.FEMALE,
                description = null)
        petDao.insertAll(pet)
        val pets = petDao.getAll().take(1).toList()
        assertThat(pets).isNotEmpty()
        assertThat(pets[0]).contains(pet)
    }

    @Test
    fun listPets() = runBlockingTest {
        val pet1 =
            Pet(
                id = 0,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)
        val pet2 =
            Pet(
                id = 1,
                name = "Lolly",
                pictureUrl = "def",
                petGender = PetGender.MALE,
                description = null)
        val pet3 =
            Pet(
                id = 2,
                name = "Duke",
                pictureUrl = "ghi",
                petGender = PetGender.FEMALE,
                description = null)
        val petList = listOf(pet1, pet2, pet3)

        petDao.insertAll(pet1, pet2, pet3)
        val fetchedPets = petDao.getAll().take(1).toList()
        assertThat(fetchedPets).isNotEmpty()
        assertThat(fetchedPets).contains(petList)
    }

    @Test
    fun adoptPet() = runBlockingTest {
        val pet =
            Pet(
                id = 0,
                name = "Ruby",
                pictureUrl = "",
                petGender = PetGender.FEMALE,
                description = null)

        // Check that there's no pet in DB
        val pets = petDao.getAll().take(1).toList()
        assertThat(pets).isNotEmpty()
        assertThat(pets[0]).isEmpty()

        petDao.insertAll(pet)

        // Check that there's no adoption in DB
        val adopted = petDao.getAdoptedPet()
        assertThat(adopted).isNull()

        val adoption = Adoption(0, pet)

        // Check that we have an adoption now
        petDao.adopt(adoption)
        assertThat(petDao.getAdoptedPet()).isEqualTo(adoption)
    }

    @Test
    fun fetchPet() = runBlockingTest {
        val pet =
            Pet(
                id = 22,
                name = "Ruby",
                pictureUrl = "abc",
                petGender = PetGender.FEMALE,
                description = null)

        // Check that there's no pet in DB
        val pets = petDao.getAll().take(1).toList()
        assertThat(pets).isNotEmpty()
        assertThat(pets[0]).isEmpty()

        petDao.insertAll(pet)

        // Check pet fetched by Id
        val fetchedPetList = petDao.fetchPet(22).take(1).toList()
        assertThat(fetchedPetList).isNotEmpty()
        assertThat(fetchedPetList[0]).isEqualTo(pet)
    }
}
