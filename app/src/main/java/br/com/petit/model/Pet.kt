package br.com.petit.model

/*
 * Disclaimer: I don't know if we should technically call it a gender
 */
enum class PetGender{
    MALE,
    FEMALE,
}


data class Pet(
    val id: Long,
    val name:String,
    val pictureUrl: String,
    val petGender: PetGender,
    val description: String,
)
