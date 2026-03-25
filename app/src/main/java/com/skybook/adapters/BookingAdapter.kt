package com.skybook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skybook.databinding.ItemBookingCardBinding
import com.skybook.models.BookingResponse

class BookingAdapter(private val onClick: (BookingResponse) -> Unit) :
    ListAdapter<BookingResponse, BookingAdapter.BookingViewHolder>(BookingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding = ItemBookingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookingViewHolder(private val binding: ItemBookingCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(booking: BookingResponse) {
            binding.tvBRef.text = booking.bookingRef
            binding.tvBRoute.text = "${booking.source} → ${booking.destination}"
            binding.tvBStatus.text = booking.status
            binding.root.setOnClickListener { onClick(booking) }
        }
    }

    class BookingDiffCallback : DiffUtil.ItemCallback<BookingResponse>() {
        override fun areItemsTheSame(oldItem: BookingResponse, newItem: BookingResponse) = oldItem.bookingRef == newItem.bookingRef
        override fun areContentsTheSame(oldItem: BookingResponse, newItem: BookingResponse) = oldItem == newItem
    }
}
