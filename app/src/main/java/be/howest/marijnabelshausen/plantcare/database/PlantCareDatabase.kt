package be.howest.marijnabelshausen.plantcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.howest.marijnabelshausen.plantcare.domain.PlantImage

@Database(entities = [PlantImage::class], version = 1, exportSchema = false)
abstract class PlantCareDatabase: RoomDatabase() {

    abstract val plantCareDao: PlantCareDao

    companion object {

        @Volatile
        private var INSTANCE: PlantCareDatabase? = null

        fun getInstance(context: Context): PlantCareDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlantCareDatabase::class.java,
                        "plant_care_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}