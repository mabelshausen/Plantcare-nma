package be.howest.marijnabelshausen.plantcare.plant

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marijnabelshausen.plantcare.database.PlantCareDao

class PlantViewModelFactory(private val plantId: Int,
                            private val dataSource: PlantCareDao,
                            private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            return PlantViewModel(plantId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}