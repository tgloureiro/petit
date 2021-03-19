package br.com.petit.feature.adoption.model

import androidx.room.*
import br.com.petit.feature.pet.model.Pet

@Entity(tableName = "adoption")
data class Adoption(
    @PrimaryKey @ColumnInfo(name = "adoption_id") val adoptionId: Long = 0L,
    @Embedded val pet: Pet
)
