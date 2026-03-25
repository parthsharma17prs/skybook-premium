package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromEt = findViewById<AutoCompleteTextView>(R.id.et_from_city)
        val toEt = findViewById<AutoCompleteTextView>(R.id.et_to_city)
        val profileImg = findViewById<ImageView>(R.id.iv_profile)

        val cities = arrayOf("New York", "Los Angeles", "London", "Paris", "Dubai", "Mumbai", "Singapore", "Sydney", "Tokyo", "San Francisco")
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        fromEt.setAdapter(adapter)
        toEt.setAdapter(adapter)

        profileImg.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<FrameLayout>(R.id.btn_search_flights).setOnClickListener {
            val from = fromEt.text.toString()
            val to = toEt.text.toString()
            
            val intent = Intent(this, FlightResultsActivity::class.java).apply {
                putExtra("FROM_CITY", from)
                putExtra("TO_CITY", to)
            }
            startActivity(intent)
        }
        
        // Seed data
        com.skybook.local.DataInitializer.seedData(this)
    }
}
