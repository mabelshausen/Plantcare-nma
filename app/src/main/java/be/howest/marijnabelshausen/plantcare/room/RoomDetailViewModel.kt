package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.marijnabelshausen.plantcare.domain.Room

class RoomDetailViewModel : ViewModel() {

    private val _room = MutableLiveData<Room>()

    private val _navigateToRoomForm = MutableLiveData<Int?>()
    val navigateToRoomForm
        get() = _navigateToRoomForm

    init {
        getRoom()
    }

    private fun getRoom() {
        TODO("Not yet implemented")
    }

    fun onEditButtonClicked() {
        _navigateToRoomForm.value = _room.value?.id
    }

    fun onRoomFormNavigated() {
        _navigateToRoomForm.value = null
    }
}