package com.skybook.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.skybook.R
import com.skybook.utils.PrefsManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val movingPlane = findViewById<ImageView>(R.id.iv_moving_plane)
        val logoGroup = findViewById<LinearLayout>(R.id.ll_logo)
        val progressBar = findViewById<ProgressBar>(R.id.pb_loading)

        val screenWidth = resources.displayMetrics.widthPixels.toFloat()

        // Phase 1: Plane flies across screen (left to right)
        movingPlane.translationX = -screenWidth
        movingPlane.alpha = 1f

        val flyAcross = ObjectAnimator.ofFloat(movingPlane, "translationX", -screenWidth, screenWidth * 1.2f).apply {
            duration = 1400
            interpolator = AccelerateDecelerateInterpolator()
        }

        // Phase 2: Logo fades in from below
        logoGroup.translationY = 80f
        val logoFadeIn = ObjectAnimator.ofFloat(logoGroup, "alpha", 0f, 1f).apply { duration = 800 }
        val logoSlideUp = ObjectAnimator.ofFloat(logoGroup, "translationY", 80f, 0f).apply { duration = 800 }

        // Phase 3: Progress bar fades in
        val pbFadeIn = ObjectAnimator.ofFloat(progressBar, "alpha", 0f, 1f).apply { duration = 500 }

        val set = AnimatorSet()
        set.playSequentially(
            flyAcross,
            AnimatorSet().apply { playTogether(logoFadeIn, logoSlideUp) },
            pbFadeIn
        )
        set.start()

        progressBar.visibility = View.VISIBLE
        progressBar.alpha = 0f

        val prefs = PrefsManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefs.isLoggedIn()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, GetStartedActivity::class.java))
            }
            finish()
        }, 3200)
    }
}
