package be.howest.marijnabelshausen.plantcare.water

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import be.howest.marijnabelshausen.plantcare.databinding.PlantListViewItemBinding
import be.howest.marijnabelshausen.plantcare.domain.Plant

class PlantAdapter(val clickListener: PlantListener) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

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
        holder.bind(item, clickListener)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(val binding: PlantListViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant, clickListener: PlantListener) {
            binding.plant = item
            binding.plantName.text = item.name
            binding.nextWaterTime.text = "Placeholder"

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