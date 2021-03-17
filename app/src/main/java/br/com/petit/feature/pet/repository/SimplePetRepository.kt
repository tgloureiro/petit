package br.com.petit.feature.pet.repository

import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class SimplePetRepository @Inject constructor() : PetRepository {
    private val petList =
        listOf(
            Pet(
                0,
                "Rudy",
                "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
                PetGender.MALE,
                "Description"),
            Pet(
                1,
                "Holly",
                "https://images.dog.ceo/breeds/kuvasz/n02104029_4091.jpg",
                PetGender.FEMALE,
                "Description"),
            Pet(
                2,
                "Maddie",
                "https://images.dog.ceo/breeds/cotondetulear/IMG_20160830_202631573.jpg",
                PetGender.FEMALE,
                "Description"),
            Pet(
                3,
                "Roxy",
                "https://images.dog.ceo/breeds/pointer-german/n02100236_1553.jpg",
                PetGender.FEMALE,
                "Description"),
            Pet(
                4,
                "Zoey",
                "https://images.dog.ceo/breeds/retriever-chesapeake/n02099849_264.jpg",
                PetGender.FEMALE,
                "Description"),
            Pet(
                5,
                "Duke",
                "https://images.dog.ceo/breeds/cattledog-australian/IMG_1688.jpg",
                PetGender.MALE,
                "Description"),
        )

    private val _pets = MutableStateFlow(petList)
    private val _selectedPet = MutableSharedFlow<Pet>(replay = 1)

    override suspend fun insertPet(pet: Pet) {
        val newList = _pets.value.toMutableList()
        newList.add(pet)
        _pets.emit(newList)
    }

    override fun fetchPets(): Flow<List<Pet>> = _pets

    override fun fetchPet(petId: Long): Flow<Pet> {
        petList.firstOrNull { it.id == petId }.let {
            if (it != null) {
                CoroutineScope(Dispatchers.Main).launch { _selectedPet.emit(it) }
            }
        }

        return _selectedPet
    }
}
