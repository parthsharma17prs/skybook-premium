package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.skybook.R
import com.skybook.local.AppDatabase
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

        val bookingId = intent.getIntExtra("BOOKING_ID", -1)
        val seatsFromIntent = intent.getStringExtra("SEATS") ?: ""
        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)
        val prefs = PrefsManager(this)

        findViewById<ImageView>(R.id.btn_back_confirm).setOnClickListener { onBackPressed() }

        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@ConfirmationActivity).dao()

            // Try to get booking by ID
            val bookings = dao.getUserBookings(prefs.getUserId())
            val target = bookings.firstOrNull { it.booking.id == bookingId } ?: bookings.firstOrNull()

            if (target != null) {
                val b = target.booking
                val f = target.flight

                // Route
                findView<TextView>(R.id.tv_ticket_from_city)?.text = f.fromCity
                findView<TextView>(R.id.tv_ticket_from_code)?.text = f.fromCode
                findView<TextView>(R.id.tv_ticket_to_city)?.text = f.toCity
                findView<TextView>(R.id.tv_ticket_to_code)?.text = f.toCode
                findView<TextView>(R.id.tv_ticket_duration)?.text = f.duration

                // Details
                findView<TextView>(R.id.tv_ticket_passenger)?.text = prefs.getUserName() ?: "Traveller"
                findView<TextView>(R.id.tv_ticket_date)?.text = b.bookingDate
                findView<TextView>(R.id.tv_ticket_class)?.text = f.classType
                findView<TextView>(R.id.tv_ticket_flight_no)?.text = "SK-${b.id + 100}"
                findView<TextView>(R.id.tv_ticket_seat)?.text = b.seatNumber
                findView<TextView>(R.id.tv_ticket_price)?.text = if (totalPrice > 0) "\$${String.format("%.0f", totalPrice)}" else "\$${String.format("%.0f", f.price)}"
                findView<TextView>(R.id.tv_ticket_time)?.text = "${f.departureTime} – ${f.arrivalTime}"

                // Airline logo
                val resId = resources.getIdentifier(f.airlineLogo, "drawable", packageName)
                if (resId != 0) findView<ImageView>(R.id.iv_ticket_airline)?.setImageResource(resId)
                
                // Show pop-up notification
                com.skybook.utils.NotificationHelper.showBookingNotification(this@ConfirmationActivity, f.toCity, b.seatNumber)
            }
        }

        findViewById<FrameLayout>(R.id.btn_book_flight).setOnClickListener {
            val intent = Intent(this, BookingHistoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun <T : android.view.View> findView(id: Int): T? = try { findViewById(id) } catch (e: Exception) { null }
}
