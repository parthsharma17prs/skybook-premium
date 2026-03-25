package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.skybook.R

class FlightResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_results)

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        findViewById<CardView>(R.id.flight_card_delta).setOnClickListener {
            startActivity(Intent(this, SeatSelectorActivity::class.java))
        }

        findViewById<CardView>(R.id.flight_card_united).setOnClickListener {
            startActivity(Intent(this, SeatSelectorActivity::class.java))
        }
    }
}
