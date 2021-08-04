package be.howest.marijnabelshausen.plantcare.domain

import androidx.room.Entity

@Entity(tableName = "plant_image")
data class PlantImage (
    val plantId: Int,
    val imagePath: String,
    val timeStamp: String
)