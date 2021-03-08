package br.com.petit.model

import androidx.room.*

@Entity(tableName = "adoption")
data class Adoption(
    @PrimaryKey @ColumnInfo(name = "adoption_id") val adoptionId: Long = 0L,
    @Embedded val pet: Pet?
)
