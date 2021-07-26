package be.howest.marijnabelshausen.plantcare.room

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.RoomDetailFragmentBinding

class RoomDetailFragment : Fragment() {

    companion object {
        fun newInstance() = RoomDetailFragment()
    }

    private val viewModel: RoomDetailViewModel by lazy {
        ViewModelProvider(this,
            RoomDetailViewModelFactory(
                RoomDetailFragmentArgs.fromBundle(requireArguments()).roomId))
            .get(RoomDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RoomDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.navigateToRoomForm.observe(viewLifecycleOwner, Observer { roomId ->
            roomId?.let {
                this.findNavController().navigate(RoomDetailFragmentDirections.actionRoomDetailFragmentToRoomFormFragment(roomId))
                viewModel.onRoomFormNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_button_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.editButton -> {
                viewModel.onEditButtonClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}