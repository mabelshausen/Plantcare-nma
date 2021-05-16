package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Plant
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception

class PlantViewModel(private val plantId: Int = 0) : ViewModel() {

    private val _plant = MutableLiveData<Plant>()

    val plant : LiveData<Plant>
        get() = _plant

    init {
        getPlant()
    }

    private fun getPlant() {
        viewModelScope.launch {
            try {
                _plant.value = PlantCareApi.retrofitService.getPlantById(plantId)
            } catch (e: Exception) {
                //TODO
            }
        }
    }
}