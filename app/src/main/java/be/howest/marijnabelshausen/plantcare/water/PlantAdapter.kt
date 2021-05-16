package be.howest.marijnabelshausen.plantcare.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.howest.marijnabelshausen.plantcare.R
import be.howest.marijnabelshausen.plantcare.domain.Plant

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

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
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        val plantName: TextView = itemView.findViewById(R.id.plant_name)
        val nextWaterTime: TextView = itemView.findViewById(R.id.next_water_time)
        val waterImage: ImageView = itemView.findViewById(R.id.water_image)

        fun bind(item: Plant) {
            plantName.text = item.name
            nextWaterTime.text = "Placeholder"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.plant_list_view_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}