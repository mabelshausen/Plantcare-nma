package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception

class WaterViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getPlants()
    }

    private fun getPlants() {
        viewModelScope.launch {
            try {
                val listResult = PlantCareApi.retrofitService.getPlants()
                _response.value = "Success: ${listResult.size} Plants retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: " + e.message
            }
        }
    }
}