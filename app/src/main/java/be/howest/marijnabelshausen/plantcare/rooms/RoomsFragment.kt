package be.howest.marijnabelshausen.plantcare.rooms

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import be.howest.marijnabelshausen.plantcare.databinding.RoomsFragmentBinding
import be.howest.marijnabelshausen.plantcare.domain.Room

class RoomsFragment : Fragment() {

    companion object {
        fun newInstance() = RoomsFragment()
    }

    private val viewModel: RoomsViewModel by lazy {
        ViewModelProvider(this).get(RoomsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RoomsFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        val adapter = RoomAdapter(RoomListener { roomId ->
            viewModel.onRoomClicked(roomId)
        })
        binding.roomList.adapter = adapter

        viewModel.rooms.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

}