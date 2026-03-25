package com.skybook.activities

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.skybook.SkyBookApplication
import com.skybook.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Airplane vector flies from bottom to top
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()
        binding.ivPlane.translationY = screenHeight / 2
        ObjectAnimator.ofFloat(binding.ivPlane, "translationY", 0f).apply {
            duration = 1200
            interpolator = DecelerateInterpolator()
            start()
        }

        // 2. App name fades in only (no scaling/morphing)
        binding.tvAppName.alpha = 0f
        binding.tvAppName.animate()
            .alpha(1f)
            .setDuration(800)
            .setStartDelay(400)
            .setInterpolator(DecelerateInterpolator())
            .start()

        // 3. Background gradient animation
        val colorStart = resources.getColor(com.skybook.R.color.gradientStart, theme)
        val colorEnd = resources.getColor(com.skybook.R.color.gradientEnd, theme)
        val gradient = binding.rootLayout.background as GradientDrawable
        
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                // Simulate shifting gradient for aesthetics
            }
            start()
        }

        // Navigate after 2.5s
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GetStartedActivity::class.java))
            finish()
        }, 2500)
    }
}
