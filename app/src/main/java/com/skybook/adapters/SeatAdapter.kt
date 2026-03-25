package com.skybook.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R

class SeatAdapter(
    private val totalSeats: Int,
    private val onSeatSelected: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    private val selectedSeats = mutableSetOf<Int>()
    private val takenSeats = (0 until totalSeats).filter { it % 7 == 0 || it % 11 == 0 }.toSet()

    class SeatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivSeat: ImageView = view.findViewById(R.id.iv_seat_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seat, parent, false)
        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val isTaken = takenSeats.contains(position)
        val isSelected = selectedSeats.contains(position)

        when {
            isTaken -> {
                holder.ivSeat.setColorFilter(Color.parseColor("#4D555555"), android.graphics.PorterDuff.Mode.SRC_IN)
                holder.itemView.setOnClickListener(null)
            }
            isSelected -> {
                holder.ivSeat.setColorFilter(Color.parseColor("#EC4899"), android.graphics.PorterDuff.Mode.SRC_IN) // Magenta for selected
                holder.itemView.setOnClickListener { toggleSelection(position) }
            }
            else -> {
                holder.ivSeat.setColorFilter(Color.parseColor("#00FF00"), android.graphics.PorterDuff.Mode.SRC_IN) // Green for available
                holder.itemView.setOnClickListener { toggleSelection(position) }
            }
        }
    }

    private fun toggleSelection(pos: Int) {
        if (selectedSeats.contains(pos)) {
            selectedSeats.remove(pos)
            onSeatSelected(selectedSeats.size, false)
        } else {
            selectedSeats.add(pos)
            onSeatSelected(selectedSeats.size, true)
        }
        notifyItemChanged(pos)
    }

    override fun getItemCount() = totalSeats
}
