package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skybook.SkyBookApplication
import com.skybook.databinding.ActivityBookingBinding
import com.skybook.models.ApiResponse
import com.skybook.models.CreateBookingRequest
import com.skybook.utils.toast
import com.skybook.viewmodels.BookingViewModel

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var viewModel: BookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val flightId = intent.getIntExtra("FLIGHT_ID", 0)
        val seatId = intent.getIntExtra("SEAT_ID", 0)

        val app = application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BookingViewModel(app.bookingRepository) as T
            }
        })[BookingViewModel::class.java]

        observeViewModel()

        binding.btnCompleteBooking.setOnClickListener {
            val firstName = binding.etPassFirstName.text.toString()
            val lastName = binding.etPassLastName.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty()) {
                toast("Please enter passenger details")
                return@setOnClickListener
            }

            val request = CreateBookingRequest(
                flightId = flightId,
                seatId = seatId,
                passengerFirstName = firstName,
                passengerLastName = lastName,
                passengerEmail = app.prefsManager.getUserName() ?: "", // Mock
                passengerPhone = "9876543210",
                paymentMethod = "CreditCard",
                baggageExtraKg = 0,
                mealPreference = "Veg"
            )
            viewModel.createBooking(request)
        }
    }

    private fun observeViewModel() {
        viewModel.bookingResult.observe(this) { response ->
            when (response.status) {
                ApiResponse.Status.LOADING -> {
                    binding.btnCompleteBooking.isEnabled = false
                    binding.btnCompleteBooking.text = "Processing..."
                }
                ApiResponse.Status.SUCCESS -> {
                    val intent = Intent(this, ConfirmationActivity::class.java).apply {
                        putExtra("BOOKING_REF", response.data?.bookingRef)
                    }
                    startActivity(intent)
                    finish()
                }
                ApiResponse.Status.ERROR -> {
                    binding.btnCompleteBooking.isEnabled = true
                    binding.btnCompleteBooking.text = "Pay and Book"
                    toast(response.message ?: "Error")
                }
            }
        }
    }
}
