package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.howest.marijnabelshausen.plantcare.databinding.PlantFragmentBinding

class PlantFragment : Fragment() {

    companion object {
        fun newInstance() = PlantFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantFragmentBinding.inflate(inflater)

        val args = PlantFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = PlantViewModelFactory(args.plantId)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PlantViewModel::class.java)

        binding.plantViewModel = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.waterButton.setOnClickListener { viewModel.waterPlant() }

        return binding.root
    }

}