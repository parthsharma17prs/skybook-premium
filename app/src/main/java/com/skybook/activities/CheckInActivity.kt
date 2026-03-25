package com.skybook.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skybook.SkyBookApplication
import com.skybook.databinding.ActivityCheckInBinding
import com.skybook.models.ApiResponse
import com.skybook.utils.toast
import com.skybook.viewmodels.CheckInViewModel

class CheckInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckInBinding
    private lateinit var viewModel: CheckInViewModel
    private var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ref = intent.getStringExtra("BOOKING_REF") ?: ""

        val app = application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CheckInViewModel(app.bookingRepository) as T
            }
        })[CheckInViewModel::class.java]

        observeViewModel()
        viewModel.fetchBoardingPass(ref)

        binding.btnFlip.setOnClickListener {
            flipCard()
        }
    }

    private fun observeViewModel() {
        viewModel.boardingPass.observe(this) { response ->
            if (response.status == ApiResponse.Status.SUCCESS) {
                val pass = response.data!!
                binding.tvBpPassenger.text = pass.passengerName
                // Set other fields...
            }
        }
    }

    private fun flipCard() {
        val root = binding.cardBoardingPass
        val front = binding.layoutFront
        val back = binding.layoutBack

        val rotation = if (isFront) 180f else 0f
        root.animate().rotationY(rotation).setDuration(600).withStartAction {
            // Halfway through animation, swap visibility
            root.postDelayed({
                if (isFront) {
                    front.visibility = View.GONE
                    back.visibility = View.VISIBLE
                    back.rotationY = 180f
                } else {
                    front.visibility = View.VISIBLE
                    back.visibility = View.GONE
                }
                isFront = !isFront
            }, 300)
        }.start()
    }
}
