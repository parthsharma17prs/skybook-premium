package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.skybook.R
import com.skybook.local.AppDatabase
import kotlinx.coroutines.launch

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

        val bookingId = intent.getIntExtra("BOOKING_ID", -1)

        findViewById<ImageView>(R.id.btn_back_confirm).setOnClickListener {
            onBackPressed()
        }

        // Logic to fetch booking and flight for real display
        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@ConfirmationActivity).dao()
            // Here you would find the individual booking data. 
            // For now, keeping static but ensuring flow:
        }

        findViewById<FrameLayout>(R.id.btn_book_flight).setOnClickListener {
            // "Download Ticket" takes user to Past Bookings to see it confirmed
            val intent = Intent(this, BookingHistoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
