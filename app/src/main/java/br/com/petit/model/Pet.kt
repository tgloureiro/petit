package br.com.petit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Disclaimer: I don't know if we should technically call it a gender for dogs
 */
enum class PetGender{
    MALE,
    FEMALE,
}

@Entity(tableName = "pet")
data class Pet(
    @PrimaryKey val id: Long,
    @ColumnInfo val name:String,
    @ColumnInfo(name = "picture_url") val pictureUrl: String,
    @ColumnInfo(name = "pet_gender")  val petGender: PetGender,
    @ColumnInfo(name = "description") val description: String?,
)
