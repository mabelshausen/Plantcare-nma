package be.howest.marijnabelshausen.plantcare.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Room
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch

class RoomsViewModel : ViewModel() {


    private val _rooms = MutableLiveData<List<Room>>()

    val rooms: LiveData<List<Room>>
        get() = _rooms

    private val _navigateToRoom = MutableLiveData<Int?>()

    val navigateToRoom
        get() = _navigateToRoom

    init {
        getRooms()
    }

    private fun getRooms() {
        viewModelScope.launch {
            try {
                _rooms.value = PlantCareApi.retrofitService.getRooms()
            } catch (e: Exception) {
                // Do something
            }
        }
    }

    fun onRoomClicked(id: Int) {
        _navigateToRoom.value = id
    }

    fun onRoomNavigated() {
        _navigateToRoom.value = null
    }

    fun onAddButtonClicked() {
        TODO("Not yet implemented")
    }
}