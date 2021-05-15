package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })
    }
}