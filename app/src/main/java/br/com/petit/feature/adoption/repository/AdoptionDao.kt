package br.com.petit.feature.adoption.repository

import androidx.room.*
import br.com.petit.feature.adoption.model.Adoption
import kotlinx.coroutines.flow.Flow

@Dao
interface AdoptionDao {

    // Hack to make a Single Row Table
    @Transaction
    @Query("SELECT * FROM adoption WHERE adoption_id = 0")
    fun getAdoptedPet(): Flow<Adoption?>

    // Hack to make a Single Row Table
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun adopt(adoption: Adoption)

    // Always clean everything when cancelling
    @Query("DELETE FROM adoption") suspend fun cancelAdoption()
}
