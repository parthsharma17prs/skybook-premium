package com.skybook.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.skybook.R
import com.skybook.local.AppDatabase
import com.skybook.local.BookingEntity
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SeatSelectorActivity : AppCompatActivity() {

    private val selectedSeats = mutableSetOf<String>()
    private var pricePerSeat = 169.0
    private var flightId = -1

    // Configuration
    private val rows = listOf("1", "2", "3", "4", "5", "6", "7", "8")
    private val leftCols = listOf("A", "B", "C")
    private val rightCols = listOf("D", "E", "F")
    // Static taken seats for UI demo (like the image)
    private val baselineTaken = setOf("1B", "1C", "2B", "2C", "3A", "3C", "3F", "4D", "4F", "5A", "5D", "5E", "6F", "7A", "7B", "8C", "8D", "8F")

    // UI refs
    private lateinit var tvSeatsInfo: TextView
    private lateinit var tvSeatLabels: TextView
    private lateinit var tvTotalPrice: TextView

    private val allTakenSeats = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seats)

        flightId = intent.getIntExtra("FLIGHT_ID", -1)
        val prefs = PrefsManager(this)
        val userId = prefs.getUserId()

        tvSeatsInfo   = findViewById(R.id.tv_seats_info)
        tvSeatLabels  = findViewById(R.id.tv_selected_seat_labels)
        tvTotalPrice  = findViewById(R.id.tv_total_price_seats)

        allTakenSeats.clear()
        allTakenSeats.addAll(baselineTaken)

        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@SeatSelectorActivity).dao()
            val flight = dao.getFlightById(flightId)
            if (flight != null) pricePerSeat = flight.price
            
            val dbBooked = dao.getBookedSeats(flightId)
            dbBooked.forEach { seatStr ->
                seatStr.split(", ").forEach { allTakenSeats.add(it.trim()) }
            }
            
            buildSeatGrid()
        }

        findViewById<View>(R.id.btn_back_seats).setOnClickListener { onBackPressed() }

        buildSeatGrid()

        // Confirm
        findViewById<FrameLayout>(R.id.btn_confirm_seat).setOnClickListener {
            if (selectedSeats.isEmpty()) {
                Toast.makeText(this, "Please select at least one seat", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val today = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            val seatStr = selectedSeats.sorted().joinToString(", ")
            lifecycleScope.launch {
                val dao = AppDatabase.getDatabase(this@SeatSelectorActivity).dao()
                val bookingId = dao.bookTicket(
                    BookingEntity(
                        userId = userId,
                        flightId = flightId,
                        seatNumber = seatStr,
                        bookingDate = today,
                        status = "CONFIRMED"
                    )
                )

                // Show professional success pop-up
                AlertDialog.Builder(this@SeatSelectorActivity)
                    .setTitle("Booking Successful! ✈️")
                    .setMessage("Your ticket for seats $seatStr has been confirmed. You can view your ticket now.")
                    .setCancelable(false)
                    .setPositiveButton("View Ticket") { _, _ ->
                        val intent = Intent(this@SeatSelectorActivity, ConfirmationActivity::class.java)
                        intent.putExtra("BOOKING_ID", bookingId.toInt())
                        intent.putExtra("SEATS", seatStr)
                        intent.putExtra("TOTAL_PRICE", selectedSeats.size * pricePerSeat)
                        startActivity(intent)
                        finish()
                    }
                    .show()
            }
        }
    }

    private fun buildSeatGrid() {
        val container = findViewById<LinearLayout>(R.id.seat_rows_container)
        container.removeAllViews()

        val margin = (6 * resources.displayMetrics.density).toInt()
        val aisleMargin = (32 * resources.displayMetrics.density).toInt()

        for (row in rows) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                setPadding(0, margin/2, 0, margin/2)
            }

            // Left Block (A, B, C)
            for (col in leftCols) {
                rowLayout.addView(makeSeatView(col + row))
            }

            // Aisle
            val aisle = Space(this).apply { layoutParams = LinearLayout.LayoutParams(aisleMargin, 1) }
            rowLayout.addView(aisle)

            // Right Block (D, E, F)
            for (col in rightCols) {
                rowLayout.addView(makeSeatView(col + row))
            }

            container.addView(rowLayout)
        }
    }

    private fun makeSeatView(seatId: String): TextView {
        val isTaken = allTakenSeats.contains(seatId)
        val sizeDp = (38 * resources.displayMetrics.density).toInt()
        val margin = (4 * resources.displayMetrics.density).toInt()

        return TextView(this).apply {
            text = seatId
            textSize = 10sp.toFloat()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(sizeDp, sizeDp).apply {
                setMargins(margin, margin, margin, margin)
            }
            
            typeface = Typeface.DEFAULT_BOLD
            
            if (isTaken) {
                setBackgroundResource(R.drawable.bg_seat_booked)
                setTextColor(Color.parseColor("#757575"))
                alpha = 0.8f
            } else {
                setBackgroundResource(R.drawable.bg_seat_available)
                setTextColor(Color.parseColor("#333333"))
                setOnClickListener { toggleSeat(seatId, this) }
            }
        }
    }

    private fun toggleSeat(seatId: String, view: TextView) {
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId)
            view.setBackgroundResource(R.drawable.bg_seat_available)
            view.setTextColor(Color.parseColor("#333333"))
        } else {
            selectedSeats.add(seatId)
            view.setBackgroundResource(R.drawable.bg_seat_selected)
            view.setTextColor(Color.BLACK)
        }
        updateBottomPanel()
    }

    private fun updateBottomPanel() {
        val count = selectedSeats.size
        tvSeatsInfo.text = "$count Seat${if (count != 1) "s" else ""} Selected"
        tvSeatLabels.text = if (selectedSeats.isEmpty()) "" else selectedSeats.sorted().joinToString(", ")
        tvTotalPrice.text = "$${String.format("%.2f", count * pricePerSeat)}"
    }
}
