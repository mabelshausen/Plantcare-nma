package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlantViewModelFactory(private val plantId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            return PlantViewModel(plantId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}