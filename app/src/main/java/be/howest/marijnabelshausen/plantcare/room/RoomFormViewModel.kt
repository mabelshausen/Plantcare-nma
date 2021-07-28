package be.howest.marijnabelshausen.plantcare.room

import android.content.res.Resources
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

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    init {
        if (isEdit) {
            getRoom()
        } else {
            _name.value = Resources.getSystem().getString(R.string.room_name)
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

    fun onSaveButtonClicked() {
        if (isEdit) {
            editRoom()
        } else {
            addRoom()
        }
    }

    private fun addRoom() {
        TODO("Not yet implemented")
    }

    private fun editRoom() {
        TODO("Not yet implemented")
    }
}