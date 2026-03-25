package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.skybook.R
import com.skybook.utils.PrefsManager

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = PrefsManager(this)

        findViewById<ImageView>(R.id.btn_back_profile).setOnClickListener { onBackPressed() }

        findViewById<TextView>(R.id.tv_profile_name).text = prefs.getUserName()
        findViewById<TextView>(R.id.tv_profile_email).text = prefs.getUserEmail() // will add getter

        findViewById<CardView>(R.id.btn_view_bookings).setOnClickListener {
            startActivity(Intent(this, BookingHistoryActivity::class.java))
        }

        findViewById<CardView>(R.id.btn_logout).setOnClickListener {
            prefs.clear()
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
