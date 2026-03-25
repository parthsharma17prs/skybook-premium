package com.skybook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skybook.databinding.ItemFlightCardBinding
import com.skybook.models.Flight

class FlightAdapter(private val onFlightClick: (Flight) -> Unit) :
    ListAdapter<Flight, FlightAdapter.FlightViewHolder>(FlightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = ItemFlightCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight, position)
    }

    inner class FlightViewHolder(private val binding: ItemFlightCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: Flight, position: Int) {
            binding.tvAirline.text = flight.airline
            binding.tvFlightNumber.text = flight.flightNumber
            binding.tvPrice.text = "₹${flight.currentPrice}"
            binding.tvDepTime.text = flight.departureTime.split("T")[1].substring(0, 5)
            binding.tvArrTime.text = flight.arrivalTime.split("T")[1].substring(0, 5)

            // Staggered enter animation
            binding.root.translationX = 100f
            binding.root.alpha = 0f
            binding.root.animate()
                .translationX(0f)
                .alpha(1f)
                .setDuration(400)
                .setStartDelay(50L * position)
                .start()

            binding.root.setOnClickListener { onFlightClick(flight) }
        }
    }

    class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Flight, newItem: Flight) = oldItem == newItem
    }
}
