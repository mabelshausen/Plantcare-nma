package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RoomFormViewModelFactory(private val roomId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomFormViewModel::class.java)) {
            return RoomFormViewModel(roomId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}