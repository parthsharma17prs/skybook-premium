package com.skybook.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R
import com.skybook.local.AppDatabase
import com.skybook.local.BookingWithFlight
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch

class BookingHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)

        findViewById<ImageView>(R.id.btn_back_history).setOnClickListener { onBackPressed() }

        val rv = findViewById<RecyclerView>(R.id.rv_bookings)
        rv.layoutManager = LinearLayoutManager(this)

        val prefs = PrefsManager(this)
        val userId = prefs.getUserId()

        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@BookingHistoryActivity).dao()
            val bookings = dao.getUserBookings(userId)
            rv.adapter = BookingAdapter(bookings)
        }
    }
}

class BookingAdapter(private val items: List<BookingWithFlight>) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val status: TextView = view.findViewById(R.id.tv_status)
        val from: TextView = view.findViewById(R.id.tv_from_city_h)
        val to: TextView = view.findViewById(R.id.tv_to_city_h)
        val seat: TextView = view.findViewById(R.id.tv_seat_h)
        val date: TextView = view.findViewById(R.id.tv_booking_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booking_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.status.text = item.booking.status
        holder.from.text = item.flight.fromCode
        holder.to.text = item.flight.toCode
        holder.seat.text = "Seat ${item.booking.seatNumber}"
        holder.date.text = item.booking.bookingDate
        
        if (item.booking.status == "CANCELED") {
            holder.status.setBackgroundResource(R.drawable.bg_status_badge) // Add a red/gray variant if possible
            holder.status.alpha = 0.5f
        }
    }

    override fun getItemCount() = items.size
}
