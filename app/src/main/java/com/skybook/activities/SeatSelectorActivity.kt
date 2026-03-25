package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R

class SeatSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seats)

        findViewById<ImageView>(R.id.btn_back_seats).setOnClickListener {
            onBackPressed()
        }

        findViewById<FrameLayout>(R.id.btn_confirm_seat).setOnClickListener {
            startActivity(Intent(this, ConfirmationActivity::class.java))
        }
    }
}
