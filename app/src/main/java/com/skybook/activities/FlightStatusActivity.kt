package com.skybook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skybook.SkyBookApplication
import com.skybook.databinding.ActivityFlightStatusBinding
import com.skybook.models.ApiResponse
import com.skybook.utils.gone
import com.skybook.utils.toast
import com.skybook.utils.visible
import com.skybook.viewmodels.FlightViewModel

class FlightStatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlightStatusBinding
    private lateinit var viewModel: FlightViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlightStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FlightViewModel(app.flightRepository) as T
            }
        })[FlightViewModel::class.java]

        binding.btnTrack.setOnClickListener {
            val fNum = binding.etFlightNumber.text.toString()
            if (fNum.isEmpty()) {
                toast("Enter flight number")
                return@setOnClickListener
            }
            // Real implementation would call API
            // For now, mock a success result
            binding.cardStatusResult.visible()
            binding.tvStatusDetails.text = "Flight $fNum is ON TIME. Expected departure 10:45 AM."
        }
    }
}
