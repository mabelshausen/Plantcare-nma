package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import be.howest.marijnabelshausen.plantcare.databinding.WaterFragmentBinding

class WaterFragment : Fragment() {

    companion object {
        fun newInstance() = WaterFragment()
    }

    private val viewModel: WaterViewModel by lazy {
        ViewModelProvider(this).get(WaterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = WaterFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

}