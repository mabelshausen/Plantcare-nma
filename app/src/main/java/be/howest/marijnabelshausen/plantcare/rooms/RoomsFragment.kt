package be.howest.marijnabelshausen.plantcare.rooms

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.howest.marijnabelshausen.plantcare.databinding.RoomsFragmentBinding

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

        return binding.root
    }

}