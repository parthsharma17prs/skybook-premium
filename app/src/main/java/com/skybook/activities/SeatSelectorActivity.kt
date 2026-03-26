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
    private val takenSeats = setOf("1B", "1E", "2A", "2F", "3C", "3D", "4B", "5A", "6D", "7E")

    // UI refs
    private lateinit var tvSeatsInfo: TextView
    private lateinit var tvSeatLabels: TextView
    private lateinit var tvTotalPrice: TextView

    // Combine hardcoded and dynamic taken seats
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

        // Reset and init
        allTakenSeats.clear()
        allTakenSeats.addAll(takenSeats)

        // Fetch flight price and booked seats
        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@SeatSelectorActivity).dao()
            val flight = dao.getFlightById(flightId)
            if (flight != null) pricePerSeat = flight.price
            
            val dbBooked = dao.getBookedSeats(flightId)
            dbBooked.forEach { seatStr ->
                // seatStr might be "1A, 1B"
                seatStr.split(", ").forEach { allTakenSeats.add(it.trim()) }
            }
            
            buildSeatGrid()
        }

        findViewById<ImageView>(R.id.btn_back_seats).setOnClickListener { onBackPressed() }

        // Initial build (will be rebuilt once data comes)
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
                val intent = Intent(this@SeatSelectorActivity, ConfirmationActivity::class.java)
                intent.putExtra("BOOKING_ID", bookingId.toInt())
                intent.putExtra("SEATS", seatStr)
                intent.putExtra("TOTAL_PRICE", selectedSeats.size * pricePerSeat)
                startActivity(intent)
            }
        }
    }

    private fun buildSeatGrid() {
        val container = findViewById<LinearLayout>(R.id.seat_rows_container)
        container.removeAllViews()

        val dp4 = (4 * resources.displayMetrics.density).toInt()
        val dp40 = (40 * resources.displayMetrics.density).toInt()
        val dp32 = (32 * resources.displayMetrics.density).toInt()
        val dp20 = (20 * resources.displayMetrics.density).toInt()

        for (row in rows) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(0, dp4, 0, dp4)
            }

            // Row number label
            val rowLabel = TextView(this).apply {
                text = row
                textSize = 11f
                setTextColor(Color.parseColor("#AAAAAA"))
                width = dp32
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
            }
            rowLayout.addView(rowLabel)

            // Left 3 seats (A, B, C)
            for (col in leftCols) {
                rowLayout.addView(makeSeatView(row + col, dp40, dp4))
            }

            // Aisle gap
            val aisle = Space(this).apply { layoutParams = LinearLayout.LayoutParams(dp20, 1) }
            rowLayout.addView(aisle)

            // Right 3 seats (D, E, F)
            for (col in rightCols) {
                rowLayout.addView(makeSeatView(row + col, dp40, dp4))
            }

            container.addView(rowLayout)
        }
    }

    private fun makeSeatView(seatId: String, sizeDp: Int, marginDp: Int): LinearLayout {
        val isTaken = allTakenSeats.contains(seatId) // USE allTakenSeats
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            val lp = LinearLayout.LayoutParams(sizeDp, sizeDp)
            lp.setMargins(marginDp, marginDp, marginDp, marginDp)
            layoutParams = lp
        }

        val seatView = ImageView(this).apply {
            setImageResource(R.drawable.ic_seat)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams = lp
            val color = when {
                isTaken -> Color.parseColor("#303030") // DARK GRAY FOR TAKEN
                else -> Color.parseColor("#673AB7") // PURPLE FOR AVAILABLE
            }
            setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
        }
        layout.addView(seatView)

        // Label under seat
        val label = TextView(this).apply {
            text = seatId
            textSize = 8f
            setTextColor(if (isTaken) Color.parseColor("#555555") else Color.parseColor("#AAAAAA"))
            gravity = Gravity.CENTER
        }
        layout.addView(label)

        if (!isTaken) {
            layout.setOnClickListener { toggleSeat(seatId, seatView, label) }
        } else {
            layout.alpha = 0.5f
        }
        return layout
    }

    private fun toggleSeat(seatId: String, icon: ImageView, label: TextView) {
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId)
            icon.setColorFilter(Color.parseColor("#673AB7"), android.graphics.PorterDuff.Mode.SRC_IN)
            label.setTextColor(Color.parseColor("#AAAAAA"))
        } else {
            selectedSeats.add(seatId)
            icon.setColorFilter(Color.parseColor("#FFC107"), android.graphics.PorterDuff.Mode.SRC_IN) // YELLOW
            label.setTextColor(Color.parseColor("#FFC107"))
        }
        updateBottomBar()
    }

    private fun updateBottomBar() {
        val count = selectedSeats.size
        tvSeatsInfo.text = if (count == 0) "Select Seats" else "$count Seat${if (count > 1) "s" else ""} Selected"
        tvSeatLabels.text = if (selectedSeats.isEmpty()) "" else selectedSeats.sorted().joinToString(", ")
        tvTotalPrice.text = "$${String.format("%.2f", count * pricePerSeat)}"
    }
}
