package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FrameLayout>(R.id.btn_search_flights).setOnClickListener {
            val intent = Intent(this, FlightResultsActivity::class.java)
            startActivity(intent)
        }
    }
}
