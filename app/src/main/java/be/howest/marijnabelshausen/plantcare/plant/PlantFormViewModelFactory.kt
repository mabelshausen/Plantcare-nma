package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PlantFormViewModelFactory(private val plantId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantFormViewModel::class.java)) {
            return PlantFormViewModel(plantId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}