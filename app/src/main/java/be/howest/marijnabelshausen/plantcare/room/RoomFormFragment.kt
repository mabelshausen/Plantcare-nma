package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.RoomFormFragmentBinding

class RoomFormFragment : Fragment() {

    companion object {
        fun newInstance() = RoomFormFragment()
    }

    private val viewModel: RoomFormViewModel by lazy {
        ViewModelProvider(this).get(RoomFormViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RoomFormFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        return binding.root
    }

}