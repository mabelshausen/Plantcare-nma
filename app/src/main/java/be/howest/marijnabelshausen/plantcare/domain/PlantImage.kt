package be.howest.marijnabelshausen.plantcare.domain

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "plant_image", primaryKeys = ["plant_id", "image_path"])
data class PlantImage (
    @ColumnInfo(name = "plant_id")
    val plantId: Int,
    @ColumnInfo(name = "image_path")
    val imagePath: String,
    @ColumnInfo(name = "timestamp")
    val timeStamp: String
)