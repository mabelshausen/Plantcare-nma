package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.PlantFormFragmentBinding

class PlantFormFragment : Fragment() {

    companion object {
        fun newInstance() = PlantFormFragment()
    }

    private val viewModel: PlantFormViewModel by lazy {
        ViewModelProvider(this).get(PlantFormViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantFormFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        return binding.root
    }

}