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

    private val viewModel: PlantViewModel by lazy {
        ViewModelProvider(this).get(PlantViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        return binding.root
    }

}