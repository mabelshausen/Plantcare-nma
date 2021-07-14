package be.howest.marijnabelshausen.plantcare.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.howest.marijnabelshausen.plantcare.databinding.RoomListViewItemBinding
import be.howest.marijnabelshausen.plantcare.domain.Room


class RoomAdapter(val clickListener: RoomListener) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    var data = listOf<Room>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        return RoomAdapter.ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoomAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(val binding: RoomListViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Room, clickListener: RoomListener) {
            binding.room = item
            binding.roomName.text = item.name

            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RoomListViewItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class RoomListener(val clickListener: (roomId: Int) -> Unit) {
    fun onClick(room: Room) = clickListener(room.id)
}