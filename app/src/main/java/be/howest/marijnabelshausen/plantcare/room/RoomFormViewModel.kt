package be.howest.marijnabelshausen.plantcare.room

import android.content.res.Resources
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.domain.Room
import be.howest.marijnabelshausen.plantcare.network.PlantCareApi
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomFormViewModel(private val roomId: Int) : ViewModel() {

    private var isEdit = (roomId != 0)

    private val _room = MutableLiveData<Room>()

    val _name = MutableLiveData<String>()

    private val _navigateToRooms = MutableLiveData<Int?>()
    val navigateToRooms
        get() = _navigateToRooms

    private val _navigateToRoomDetail = MutableLiveData<Int?>()
    val navigateToRoomDetail
        get() = _navigateToRoomDetail

    init {
        if (isEdit) {
            getRoom()
        } else {
            _name.value = "Room Name"
        }
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

    suspend fun onSaveButtonClicked() {
        if (isEdit) {
            editRoom()
        } else {
            addRoom()
        }
    }

    private suspend fun addRoom() {
        _room.value = Room(0, _name.value!!)
        //validateRoom()
        try {
            PlantCareApi.retrofitService.addRoom(_room.value!!)
            _navigateToRooms.value = 1
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun editRoom() {
        _room.value!!.name = _name.value!!
        //validateRoom()
        try {
            PlantCareApi.retrofitService.editRoom(roomId, _room.value!!)
            _navigateToRoomDetail.value = roomId
        } catch (e: Exception) {
            throw e
        }
    }

    private fun validateRoom() {
        TODO("Not yet implemented")
    }

    fun onRoomsNavigated() {
        _navigateToRooms.value = null
    }

    fun onRoomDetailNavigated() {
        _navigateToRoomDetail.value = null
    }

}