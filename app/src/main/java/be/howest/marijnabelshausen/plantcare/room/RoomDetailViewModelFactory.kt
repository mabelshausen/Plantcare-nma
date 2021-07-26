package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RoomDetailViewModelFactory(private val roomId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomDetailViewModel::class.java)) {
            return RoomDetailViewModel(roomId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}