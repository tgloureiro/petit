package br.com.petit.model

/*
 * Disclaimer: I don't know if we shoud technicall call a gender
 */
enum class PetGender{
    male,
    female,
}


data class Pet(
    val name:String,
    val pictureUrl: String,
    val petGender: PetGender,
    val description: String
)
