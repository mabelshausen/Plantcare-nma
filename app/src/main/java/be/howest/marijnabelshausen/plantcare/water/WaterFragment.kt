package be.howest.marijnabelshausen.plantcare.water

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.database.PlantCareDatabase
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

        val adapter = PlantAdapter(PlantListener { plantId ->
            viewModel.onPlantClicked(plantId)
        }, PlantCareDatabase.getInstance(requireNotNull(this.activity).application).plantCareDao)
        binding.plantList.adapter = adapter

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToPlant.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(WaterFragmentDirections.actionWaterFragmentToPlantFragment(plantId))
                viewModel.onPlantNavigated()
            }
        })

        viewModel.navigateToPlantForm.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(WaterFragmentDirections.actionWaterFragmentToPlantFormFragment(plantId))
                viewModel.onPlantFormNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_button_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addButton -> {
                viewModel.onAddButtonClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}