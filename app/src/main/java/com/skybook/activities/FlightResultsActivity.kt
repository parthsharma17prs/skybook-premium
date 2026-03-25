package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R

class FlightResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_results)

        findViewById<LinearLayout>(R.id.flight_card_delta).setOnClickListener {
            startActivity(Intent(this, SeatSelectorActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.flight_card_southwest).setOnClickListener {
            startActivity(Intent(this, SeatSelectorActivity::class.java))
        }
    }
}
