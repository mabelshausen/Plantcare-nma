package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.domain.Room
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomDetailViewModel(private val roomId: Int = 0) : ViewModel() {

    private val _room = MutableLiveData<Room>()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name


    private val _navigateToRoomForm = MutableLiveData<Int?>()
    val navigateToRoomForm
        get() = _navigateToRoomForm

    init {
        getRoom()
    }

    private fun getRoom() {
        viewModelScope.launch {
            try {
                val room = PlantCareApi.retrofitService.getRoomById(roomId)
                _room.value = room
                _name.value = _room.value?.name
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun onEditButtonClicked() {
        _navigateToRoomForm.value = _room.value?.id
    }

    fun onRoomFormNavigated() {
        _navigateToRoomForm.value = null
    }
}