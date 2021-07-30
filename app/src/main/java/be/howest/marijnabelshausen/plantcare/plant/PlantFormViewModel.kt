package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.marijnabelshausen.plantcare.domain.Plant

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
        TODO("Not yet implemented")
    }

    fun onSaveButtonClicked() {
        TODO("Not yet implemented")
    }
}