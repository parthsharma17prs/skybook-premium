package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R
import com.skybook.utils.PrefsManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ivPlane = findViewById<android.widget.ImageView>(R.id.iv_plane)
        val tvAppName = findViewById<android.widget.TextView>(R.id.tv_app_name)

        // Premium Entry Animation
        ivPlane.translationY = 400f
        ivPlane.alpha = 0f
        ivPlane.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(1200)
            .setInterpolator(DecelerateInterpolator())
            .start()

        tvAppName.alpha = 0f
        tvAppName.animate()
            .alpha(1f)
            .setDuration(1000)
            .setStartDelay(500)
            .start()

        val prefs = PrefsManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefs.isLoggedIn()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, GetStartedActivity::class.java))
            }
            finish()
        }, 3000)
    }
}
