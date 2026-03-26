package com.skybook.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var rv: RecyclerView
    private lateinit var dao: com.skybook.local.AppDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)

        findViewById<ImageView>(R.id.btn_back_history).setOnClickListener { onBackPressed() }

        rv = findViewById(R.id.rv_bookings)
        rv.layoutManager = LinearLayoutManager(this)

        dao = AppDatabase.getDatabase(this@BookingHistoryActivity).dao()
        loadBookings()
    }

    private fun loadBookings() {
        val prefs = PrefsManager(this)
        val userId = prefs.getUserId()
        lifecycleScope.launch {
            val bookings = dao.getUserBookings(userId)
            rv.adapter = BookingAdapter(bookings) { bookingId ->
                // Cancel logic
                androidx.appcompat.app.AlertDialog.Builder(this@BookingHistoryActivity)
                    .setTitle("Cancel Ticket")
                    .setMessage("Are you sure you want to cancel this booking?")
                    .setPositiveButton("Yes, Cancel") { _, _ ->
                        lifecycleScope.launch {
                            dao.updateBookingStatus(bookingId, "CANCELLED")
                            Toast.makeText(this@BookingHistoryActivity, "Ticket Cancelled", Toast.LENGTH_SHORT).show()
                            loadBookings()
                        }
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }
    }
}

class BookingAdapter(
    private val items: List<BookingWithFlight>,
    private val onCancel: (Int) -> Unit
) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val status: TextView = view.findViewById(R.id.tv_status)
        val from: TextView = view.findViewById(R.id.tv_from_city_h)
        val to: TextView = view.findViewById(R.id.tv_to_city_h)
        val seat: TextView = view.findViewById(R.id.tv_seat_h)
        val date: TextView = view.findViewById(R.id.tv_booking_date)
        val card: View = view
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
        
        if (item.booking.status == "CANCELLED") {
            holder.status.setBackgroundResource(R.drawable.bg_status_cancelled)
            holder.status.setTextColor(android.graphics.Color.parseColor("#FF5252"))
            holder.card.alpha = 0.6f
            holder.card.setOnClickListener(null)
        } else {
            holder.status.setBackgroundResource(R.drawable.bg_status_badge)
            holder.status.setTextColor(android.graphics.Color.parseColor("#00E676"))
            holder.card.alpha = 1.0f
            holder.card.setOnClickListener { onCancel(item.booking.id) }
        }
    }

    override fun getItemCount() = items.size
}
