package be.howest.marijnabelshausen.plantcare.plant

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.howest.marijnabelshausen.plantcare.databinding.ImageGridViewItemBinding
import be.howest.marijnabelshausen.plantcare.domain.PlantImage
import java.lang.Exception

class PlantImageAdapter(): RecyclerView.Adapter<PlantImageAdapter.ViewHolder>() {

    var data = listOf<PlantImage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlantImageAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(val binding: ImageGridViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlantImage) {
            binding.plantImage = item
            binding.plantImageView.setImageBitmap(null)
            try {
                binding.plantImageView.setImageBitmap(BitmapFactory.decodeFile(item.imagePath))
            } catch (e: Exception) {
                throw e
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageGridViewItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}