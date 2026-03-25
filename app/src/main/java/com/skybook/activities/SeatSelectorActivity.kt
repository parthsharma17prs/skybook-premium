package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R
import com.skybook.adapters.SeatAdapter
import com.skybook.local.AppDatabase
import com.skybook.local.BookingEntity
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch

class SeatSelectorActivity : AppCompatActivity() {
    private var selectedSeatNo: String? = null
    private var flightId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seats)

        flightId = intent.getIntExtra("FLIGHT_ID", -1)
        val prefs = PrefsManager(this)
        val userId = prefs.getUserId()

        findViewById<ImageView>(R.id.btn_back_seats).setOnClickListener { onBackPressed() }

        val rv = findViewById<RecyclerView>(R.id.rv_seats)
        rv.layoutManager = GridLayoutManager(this, 4) // 4 seats across

        val tvInfo = findViewById<TextView>(R.id.tv_seats_info)
        val tvPrice = findViewById<TextView>(R.id.tv_total_price_seats)

        rv.adapter = SeatAdapter(40) { count, isAdded ->
            if (count > 0) {
                selectedSeatNo = "${(posToRow(count))}${posToLetter(count)}"
                tvInfo.text = "$count Seat Selected\n$selectedSeatNo"
                tvPrice.text = "$${count * 169.0}"
            } else {
                tvInfo.text = "Select Seats"
                tvPrice.text = "$0.00"
                selectedSeatNo = null
            }
        }

        findViewById<FrameLayout>(R.id.btn_confirm_seat).setOnClickListener {
            if (selectedSeatNo == null) {
                Toast.makeText(this, "Please select at least one seat", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val dao = AppDatabase.getDatabase(this@SeatSelectorActivity).dao()
                val bookingId = dao.bookTicket(
                    BookingEntity(
                        userId = userId,
                        flightId = flightId,
                        seatNumber = selectedSeatNo!!,
                        bookingDate = "27 June 2024",
                        status = "CONFIRMED"
                    )
                )
                
                val intent = Intent(this@SeatSelectorActivity, ConfirmationActivity::class.java)
                intent.putExtra("BOOKING_ID", bookingId.toInt())
                startActivity(intent)
            }
        }
    }

    private fun posToRow(pos: Int) = (pos / 4) + 1
    private fun posToLetter(pos: Int) = listOf("A", "B", "C", "D")[pos % 4]
}
