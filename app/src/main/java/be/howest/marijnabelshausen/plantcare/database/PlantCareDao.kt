package be.howest.marijnabelshausen.plantcare.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import be.howest.marijnabelshausen.plantcare.domain.PlantImage

@Dao
interface PlantCareDao {

    @Insert
    suspend fun insert(plantImage: PlantImage)

    @Query("SELECT * FROM plant_image WHERE plant_id = :plantId")
    suspend fun getByPlantId(plantId: Int): List<PlantImage>?

    @Query("SELECT * FROM plant_image WHERE plant_id = :plantId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestImage(plantId: Int): PlantImage?
}