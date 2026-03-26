package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R
import com.skybook.utils.PrefsManager

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = PrefsManager(this)

        // Populate user data
        val name = prefs.getUserName() ?: "Traveller"
        findViewById<TextView>(R.id.tv_profile_name).text = name
        findViewById<TextView>(R.id.tv_profile_email).text = prefs.getUserEmail() ?: "user@skybook.app"

        // Fetch stats
        androidx.lifecycle.lifecycleScope.launch {
            val dao = com.skybook.local.AppDatabase.getDatabase(this@ProfileActivity).dao()
            val bookings = dao.getUserBookings(prefs.getUserId())
            val count = bookings.filter { it.booking.status == "CONFIRMED" }.size
            findViewById<TextView>(R.id.tv_flights_count)?.text = count.toString()
            findViewById<TextView>(R.id.tv_dest_count)?.text = bookings.map { it.flight.toCity }.distinct().size.toString()
        }

        // Back
        findViewById<ImageView>(R.id.btn_back_profile).setOnClickListener { onBackPressed() }

        // View Bookings
        findViewById<androidx.cardview.widget.CardView>(R.id.btn_view_bookings).setOnClickListener {
            startActivity(Intent(this, BookingHistoryActivity::class.java))
        }

        // Edit Profile (toast placeholder)
        findViewById<androidx.cardview.widget.CardView>(R.id.btn_edit_profile).setOnClickListener {
            Toast.makeText(this, "Edit profile coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Notifications settings (toast placeholder)
        findViewById<androidx.cardview.widget.CardView>(R.id.btn_notifications).setOnClickListener {
            Toast.makeText(this, "Notifications settings coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Help & Support
        findViewById<androidx.cardview.widget.CardView>(R.id.btn_support).setOnClickListener {
            Toast.makeText(this, "Support: support@skybook.app", Toast.LENGTH_SHORT).show()
        }

        // Sign Out
        findViewById<androidx.cardview.widget.CardView>(R.id.btn_logout).setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes, Sign Out") { _, _ ->
                    prefs.clearSession()
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
