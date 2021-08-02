package be.howest.marijnabelshausen.plantcare.plant

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.databinding.PlantFragmentBinding

class PlantFragment : Fragment() {

    companion object {
        fun newInstance() = PlantFragment()
    }

    private val viewModel: PlantViewModel by lazy {
        ViewModelProvider(this,
            PlantViewModelFactory(
                PlantFragmentArgs.fromBundle(requireArguments()).plantId))
            .get(PlantViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantFragmentBinding.inflate(inflater)

        binding.plantViewModel = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.waterButton.setOnClickListener { viewModel.waterPlant() }
        binding.cameraButton.setOnClickListener {
            if (context?.packageManager!!.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                viewModel.cameraButtonClicked()
            }
        }

        setHasOptionsMenu(true)

        viewModel.navigateToPlantForm.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(PlantFragmentDirections.actionPlantFragmentToPlantFormFragment(plantId))
                viewModel.onPlantFormNavigated()
            }
        })

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