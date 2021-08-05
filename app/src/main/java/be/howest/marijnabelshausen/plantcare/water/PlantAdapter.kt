package be.howest.marijnabelshausen.plantcare.water

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.howest.marijnabelshausen.plantcare.database.PlantCareDao
import be.howest.marijnabelshausen.plantcare.databinding.PlantListViewItemBinding
import be.howest.marijnabelshausen.plantcare.domain.Plant
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class PlantAdapter(val clickListener: PlantListener, val database: PlantCareDao) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    var data =  listOf<Plant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener, database)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(val binding: PlantListViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant, clickListener: PlantListener, database: PlantCareDao) {
            binding.plant = item
            binding.plantName.text = item.name
            binding.waterImage.setImageBitmap(null)

            val lastWatered = LocalDateTime.parse(item.lastWatered, DateTimeFormatter.ISO_DATE_TIME)
            val diff = Duration.between(lastWatered, LocalDateTime.now())
            val waterFreqHrs = item.waterFreq.times(24)
            if (diff.toHours() >= waterFreqHrs) {
                binding.nextWaterTime.text = "Water now!"
            } else {
                binding.nextWaterTime.text = "Water in " + (waterFreqHrs - diff.toHours()) + " hours"
            }

            runBlocking {
                launch {
                    try {
                        val plantImage = database.getLatestImage(item.id)
                        if (plantImage != null) {
                            println(plantImage)
                            binding.waterImage.setImageBitmap(BitmapFactory.decodeFile(plantImage?.imagePath))
                        }
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }

            binding.clickListener  = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlantListViewItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PlantListener(val clickListener: (plantId: Int) -> Unit) {
    fun onClick(plant: Plant) = clickListener(plant.id)
}