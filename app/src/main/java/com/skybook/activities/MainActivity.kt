package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.skybook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimations()
        
        binding.btnSearchFlights.setOnClickListener {
            startActivity(Intent(this, FlightResultsActivity::class.java))
        }
    }

    private fun setupAnimations() {
        // Removed old staggered animations since the whole layout acts as a single cohesive unit now.
        // We can add specific animations later if desired.
    }
}
