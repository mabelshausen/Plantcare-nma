package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.marijnabelshausen.plantcare.domain.Room
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaterViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getRooms()
    }

    private fun getRooms() {
        PlantCareApi.retrofitService.getRooms().enqueue(
            object : Callback<List<Room>> {
                override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                    _response.value = "Success: ${response.body()?.size} Rooms retrieved"
                }

                override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })
    }
}