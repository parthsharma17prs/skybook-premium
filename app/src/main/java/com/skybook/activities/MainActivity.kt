package com.skybook.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R
import com.skybook.local.DataInitializer
import com.skybook.utils.PrefsManager
import java.util.*

class MainActivity : AppCompatActivity() {
    private var passengerCount = 1
    private var selectedClass = "Economy"
    private val classes = arrayOf("Economy", "Business", "First Class")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PrefsManager(this)

        // Greet user
        val tvGreeting = findViewById<TextView>(R.id.tv_greeting)
        tvGreeting.text = prefs.getUserName() ?: "Traveller"

        val fromEt = findViewById<AutoCompleteTextView>(R.id.et_from_city)
        val toEt = findViewById<AutoCompleteTextView>(R.id.et_to_city)
        val tvPax = findViewById<TextView>(R.id.tv_pax_count)
        val tvClass = findViewById<TextView>(R.id.tv_class)
        val tvDepartDate = findViewById<TextView>(R.id.tv_depart_date)
        val tvReturnDate = findViewById<TextView>(R.id.tv_return_date)
        val profileImg = findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.iv_profile)

        // Autocomplete suggestions
        val cities = arrayOf(
            "New York", "Los Angeles", "London", "Paris", "Dubai",
            "Mumbai", "Singapore", "Sydney", "Tokyo", "San Francisco",
            "Bangkok", "Hong Kong", "Amsterdam", "Toronto", "Chicago"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        fromEt.setAdapter(adapter)
        toEt.setAdapter(adapter)
        fromEt.threshold = 1
        toEt.threshold = 1

        // Passenger +/- buttons
        findViewById<ImageView>(R.id.btn_pax_plus).setOnClickListener {
            if (passengerCount < 9) { passengerCount++; tvPax.text = passengerCount.toString() }
        }
        findViewById<ImageView>(R.id.btn_pax_minus).setOnClickListener {
            if (passengerCount > 1) { passengerCount--; tvPax.text = passengerCount.toString() }
        }

        // Class picker
        findViewById<LinearLayout>(R.id.btn_class_selector).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Select Class")
                .setItems(classes) { _, which ->
                    selectedClass = classes[which]
                    tvClass.text = selectedClass
                }
                .show()
        }

        // Date pickers
        val cal = Calendar.getInstance()
        val dateListener = { tv: TextView ->
            DatePickerDialog(this, { _, y, m, d ->
                tv.text = "$d ${monthShort(m)} $y"
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        findViewById<LinearLayout>(R.id.btn_depart_date).setOnClickListener { dateListener(tvDepartDate) }
        findViewById<LinearLayout>(R.id.btn_return_date).setOnClickListener { dateListener(tvReturnDate) }

        // Profile button
        profileImg.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }

        // Bottom nav
        findViewById<ImageView>(R.id.nav_home).setOnClickListener { /* already home */ }
        findViewById<ImageView>(R.id.nav_search).setOnClickListener { /* click search btn */ }
        findViewById<ImageView>(R.id.nav_bookings).setOnClickListener {
            startActivity(Intent(this, BookingHistoryActivity::class.java))
        }
        findViewById<ImageView>(R.id.nav_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Search
        findViewById<FrameLayout>(R.id.btn_search_flights).setOnClickListener {
            val from = fromEt.text.toString().trim()
            val to = toEt.text.toString().trim()
            if (from.isEmpty() || to.isEmpty()) {
                Toast.makeText(this, "Please enter origin and destination", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, FlightResultsActivity::class.java).apply {
                putExtra("FROM_CITY", from)
                putExtra("TO_CITY", to)
                putExtra("PASSENGERS", passengerCount)
                putExtra("CLASS", selectedClass)
                putExtra("DEPART", tvDepartDate.text.toString())
            }
            startActivity(intent)
        }

        // Seed data on first launch
        DataInitializer.seedData(this)
    }

    private fun monthShort(month: Int) = arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")[month]
}
