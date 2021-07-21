package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Plant
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PlantViewModel(private val plantId: Int = 0) : ViewModel() {

    private val _plant = MutableLiveData<Plant>()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _sciName = MutableLiveData<String>()
    val sciName: LiveData<String>
        get() = _sciName

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int>
        get() = _age

    private val _waterFreq = MutableLiveData<Int>()
    val waterFreq: LiveData<Int>
        get() = _waterFreq

    private val _waterNext = MutableLiveData<String>()
    val waterNext: LiveData<String>
        get() = _waterNext

    private val _navigateToPlantForm = MutableLiveData<Int?>()
    val navigateToPlantForm
        get() = _navigateToPlantForm

    init {
        getPlant()
    }

    private fun getPlant() {
        viewModelScope.launch {
            try {
                val plant = PlantCareApi.retrofitService.getPlantById(plantId)
                _plant.value = plant
                _name.value = _plant.value?.name
                _sciName.value = _plant.value?.sciName
                _age.value = _plant.value?.age
                _waterFreq.value = _plant.value?.waterFreq
                fillWaterNext()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun fillWaterNext() {
        val lastWatered = LocalDateTime.parse(_plant.value?.lastWatered, DateTimeFormatter.ISO_DATE_TIME)
        val diff = Duration.between(lastWatered, LocalDateTime.now())
        val waterFreqHrs = _waterFreq.value?.times(24) ?: 0
        if (diff.toHours() >= waterFreqHrs) {
            _waterNext.value = "Water now!"
        } else {
            _waterNext.value = "Water in " + (waterFreqHrs - diff.toHours()) + " hours"
        }
    }

    fun waterPlant() {
        viewModelScope.launch {
            try {
                _plant.value?.lastWatered = LocalDateTime.now().toString()
                PlantCareApi.retrofitService.editPlant(_plant.value?.id!!, _plant.value!!)
                fillWaterNext()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun onEditButtonClicked() {
        _navigateToPlantForm.value = _plant.value?.id
    }

    fun onPlantFormNavigated() {
        _navigateToPlantForm.value = null
    }
}