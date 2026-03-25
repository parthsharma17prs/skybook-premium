package com.skybook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skybook.databinding.ActivityAdminDashboardBinding
import com.skybook.utils.toast

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdminUpdate.setOnClickListener {
            val fId = binding.etAdminFlightId.text.toString()
            val status = binding.etAdminStatus.text.toString()
            if (fId.isEmpty() || status.isEmpty()) {
                toast("Please fill all fields")
                return@setOnClickListener
            }
            toast("Broadcast sent successfully")
        }

        binding.btnAdminReprice.setOnClickListener {
            toast("Dynamic pricing engine triggered")
        }
    }
}
