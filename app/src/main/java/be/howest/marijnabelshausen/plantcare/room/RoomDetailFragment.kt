package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.RoomDetailFragmentBinding

class RoomDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RoomDetailFragment()
    }

    private val viewModel: RoomDetailViewModel by lazy {
        ViewModelProvider(this).get(RoomDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RoomDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        return binding.root
    }

}