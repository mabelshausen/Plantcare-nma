package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Plant
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception

class PlantFormViewModel(private val plantId: Int) : ViewModel() {

    private var isEdit = (plantId != 0)

    private val _plant = MutableLiveData<Plant>()

    val name = MutableLiveData<String>()
    val sciName = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val waterFreq = MutableLiveData<String>()

    private val _navigateToPlants = MutableLiveData<Int?>()
    val navigateToPlants
        get() = _navigateToPlants

    private val _navigateToPlantDetail = MutableLiveData<Int?>()
    val navigateToPlantDetail
        get() = _navigateToPlantDetail

    init {
        if (isEdit) {
            getPlant()
        } else {
            name.value = "Plant Name"
            sciName.value = "Scientific Name"
            age.value = "Age (in years)"
            waterFreq.value = "Days between watering"
        }
    }

    private fun getPlant() {
        viewModelScope.launch {
            try {
                val plant = PlantCareApi.retrofitService.getPlantById(plantId)
                _plant.value = plant
                name.value = _plant.value?.name
                sciName.value = _plant.value?.sciName
                age.value = _plant.value?.age.toString()
                waterFreq.value = _plant.value?.waterFreq.toString()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun onSaveButtonClicked() {
        if (isEdit) {
            editPlant()
        } else {
            addPlant()
        }
    }

    private fun addPlant() {
        //validatePlant()
        _plant.value = Plant(0,
            name = name.value!!,
            sciName = sciName.value!!,
            age = age.value!!.toInt(),
            waterFreq = waterFreq.value!!.toInt(),
            lastWatered = "",
            room_id = 1)
        viewModelScope.launch {
            try {
                PlantCareApi.retrofitService.addPlant(_plant.value!!)
                _navigateToPlants.value = 1
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun editPlant() {
        //validatePlant()
        _plant.value!!.name = name.value!!
        _plant.value!!.sciName = sciName.value!!
        _plant.value!!.age = age.value!!.toInt()
        _plant.value!!.waterFreq = waterFreq.value!!.toInt()
        viewModelScope.launch {
            try {
                PlantCareApi.retrofitService.editPlant(plantId, _plant.value!!)
                _navigateToPlantDetail.value = plantId
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun validatePlant() {
        TODO("Not yet implemented")
    }
}