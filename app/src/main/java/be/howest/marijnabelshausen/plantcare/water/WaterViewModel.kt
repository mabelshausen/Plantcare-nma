package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Room
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class WaterViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getRooms()
    }

    private fun getRooms() {
        viewModelScope.launch {
            try {
                val listResult = PlantCareApi.retrofitService.getRooms()
                _response.value = "Success: ${listResult.size} Rooms retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: " + e.message
            }
        }
    }
}