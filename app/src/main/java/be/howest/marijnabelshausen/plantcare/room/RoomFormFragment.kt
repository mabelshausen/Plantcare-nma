package be.howest.marijnabelshausen.plantcare.room

import android.content.res.ObbInfo
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.RoomFormFragmentBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoomFormFragment : Fragment() {

    companion object {
        fun newInstance() = RoomFormFragment()
    }

    private val viewModel: RoomFormViewModel by lazy {
        ViewModelProvider(this,
            RoomFormViewModelFactory(
                RoomFormFragmentArgs.fromBundle(requireArguments()).roomId))
            .get(RoomFormViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RoomFormFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        viewModel.navigateToRooms.observe(viewLifecycleOwner, Observer { roomId ->
            roomId?.let {
                this.findNavController().navigate(RoomFormFragmentDirections.actionRoomFormFragmentToRoomsFragment())
                viewModel.onRoomsNavigated()
            }
        })

        viewModel.navigateToRoomDetail.observe(viewLifecycleOwner, Observer { roomId ->
            roomId?.let {
                this.findNavController().navigate(RoomFormFragmentDirections.actionRoomFormFragmentToRoomDetailFragment(roomId))
                viewModel.onRoomDetailNavigated()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveButton -> {
                runBlocking {
                    launch { viewModel.onSaveButtonClicked() }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}