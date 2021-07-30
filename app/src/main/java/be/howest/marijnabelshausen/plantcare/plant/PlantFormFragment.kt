package be.howest.marijnabelshausen.plantcare.plant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.PlantFormFragmentBinding

class PlantFormFragment : Fragment() {

    companion object {
        fun newInstance() = PlantFormFragment()
    }

    private val viewModel: PlantFormViewModel by lazy {
        ViewModelProvider(this,
            PlantFormViewModelFactory(
                PlantFormFragmentArgs.fromBundle(requireArguments()).plantId))
            .get(PlantFormViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantFormFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        viewModel.navigateToPlants.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(PlantFormFragmentDirections.actionPlantFormFragmentToWaterFragment())
                viewModel.onPlantsNavigated()
            }
        })

        viewModel.navigateToPlantDetail.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(PlantFormFragmentDirections.actionPlantFormFragmentToPlantFragment(plantId))
                viewModel.onPlantDetailNavigated()
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
                viewModel.onSaveButtonClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}