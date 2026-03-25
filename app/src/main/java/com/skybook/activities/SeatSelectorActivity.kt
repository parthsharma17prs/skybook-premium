package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R

class SeatSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seats)

        findViewById<FrameLayout>(R.id.btn_confirm_seat).setOnClickListener {
            startActivity(Intent(this, ConfirmationActivity::class.java))
        }
    }
}
