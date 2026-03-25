package com.skybook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R
import com.skybook.databinding.ItemSeatBinding
import com.skybook.models.Seat

class SeatAdapter(private val onSeatClick: (Seat) -> Unit) :
    ListAdapter<Seat, SeatAdapter.SeatViewHolder>(SeatDiffCallback()) {

    private var selectedSeat: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = ItemSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SeatViewHolder(private val binding: ItemSeatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seat: Seat) {
            binding.tvSeatNum.text = seat.seatNumber
            
            val bg = when {
                seat.seatNumber == selectedSeat -> R.drawable.bg_seat_selected
                !seat.isAvailable || seat.isLocked -> R.drawable.bg_seat_booked
                else -> R.drawable.bg_seat_available
            }
            binding.viewSeat.setBackgroundResource(bg)

            binding.root.setOnClickListener {
                if (seat.isAvailable && !seat.isLocked) {
                    val oldSelected = selectedSeat
                    selectedSeat = seat.seatNumber
                    // Bounce animation
                    binding.root.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction {
                        binding.root.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
                    }.start()
                    
                    onSeatClick(seat)
                    notifyDataSetChanged() // Simplified
                }
            }
        }
    }

    class SeatDiffCallback : DiffUtil.ItemCallback<Seat>() {
        override fun areItemsTheSame(oldItem: Seat, newItem: Seat) = oldItem.seatNumber == newItem.seatNumber
        override fun areContentsTheSame(oldItem: Seat, newItem: Seat) = oldItem == newItem
    }
}
