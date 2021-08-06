package be.howest.marijnabelshausen.plantcare.plant

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.database.PlantCareDatabase
import be.howest.marijnabelshausen.plantcare.databinding.PlantFragmentBinding
import be.howest.marijnabelshausen.plantcare.water.PlantAdapter
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PlantFragment : Fragment() {

    lateinit var currentPhotoPath: String
    lateinit var currentTimeStamp: String

    val REQUEST_IMAGE_CAPTURE = 1

    companion object {
        fun newInstance() = PlantFragment()
    }

    private val viewModel: PlantViewModel by lazy {
        ViewModelProvider(this,
            PlantViewModelFactory(
                PlantFragmentArgs.fromBundle(requireArguments()).plantId,
                PlantCareDatabase.getInstance(requireNotNull(this.activity).application).plantCareDao,
                requireNotNull(this.activity).application))
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
                dispatchTakePictureIntent(requireContext())
            }
        }

        setHasOptionsMenu(true)

        viewModel.navigateToPlantForm.observe(viewLifecycleOwner, Observer { plantId ->
            plantId?.let {
                this.findNavController().navigate(PlantFragmentDirections.actionPlantFragmentToPlantFormFragment(plantId))
                viewModel.onPlantFormNavigated()
            }
        })

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.imageGrid.layoutManager = manager

        val adapter = PlantImageAdapter()
        binding.imageGrid.adapter = adapter

        viewModel.images.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.addPlantImage(currentPhotoPath, currentTimeStamp)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile("JPEG_${timestamp}_", ".jpeg", storageDir).apply {
            currentPhotoPath = absolutePath
            currentTimeStamp = timestamp
        }
    }

    private fun dispatchTakePictureIntent(context: Context) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile(context)
                } catch (e : IOException) {
                    throw e
                }

                photoFile?.also {
                    val photoURI = FileProvider.getUriForFile(
                        context,
                        "be.howest.marijnabelshausen.plantcare.fileprovider",
                        it)

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE, null)
                }
            }
        }
    }

}