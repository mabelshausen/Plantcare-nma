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

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _sciName = MutableLiveData<String>()
    val sciName: LiveData<String>
        get() = _sciName

    private val _age = MutableLiveData<String>()
    val age: LiveData<String>
        get() = _age


    init {
        getPlant()
        _name.value = _plant.value?.name
        _sciName.value = _plant.value?.sciName
        _age.value = _plant.value?.age.toString()
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