package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skybook.SkyBookApplication
import com.skybook.databinding.ActivityAuthBinding
import com.skybook.models.ApiResponse
import com.skybook.models.LoginRequest
import com.skybook.models.RegisterRequest
import com.skybook.utils.toast
import com.skybook.viewmodels.AuthViewModel

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel
    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(app.authRepository, app.prefsManager) as T
            }
        })[AuthViewModel::class.java]

        setupAnimations()
        setupListeners()
        observeViewModel()
    }

    private fun setupAnimations() {
        // Login card slides up from bottom
        binding.loginCard.translationY = 300f
        binding.loginCard.animate()
            .translationY(0f)
            .setDuration(500)
            .setInterpolator(OvershootInterpolator())
            .start()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            // Scale animation on click
            it.animate().scaleX(0.95f).scaleY(0.95f).setDuration(50).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()

            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phone = binding.etPhone.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                toast("Please fill all fields")
                return@setOnClickListener
            }

            if (isLoginMode) {
                viewModel.login(LoginRequest(email, pass))
            } else {
                if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
                    toast("Please fill all details for registration")
                    return@setOnClickListener
                }
                viewModel.register(RegisterRequest(firstName, lastName, email, pass, phone))
            }
        }

        binding.tvSwitchAuth.setOnClickListener {
            isLoginMode = !isLoginMode
            binding.btnLogin.text = if (isLoginMode) "Sign In" else "Create Account"
            binding.tvSwitchAuth.text = if (isLoginMode) "New user? Create an account" else "Have an account? Sign In"
            binding.tvSubtitle.text = if (isLoginMode) "Log in to start your journey" else "Join us for a better travel experience"
            
            val visibility = if (isLoginMode) android.view.View.GONE else android.view.View.VISIBLE
            binding.tilFirstName.visibility = visibility
            binding.tilLastName.visibility = visibility
            binding.tilPhone.visibility = visibility
        }
    }

    private fun observeViewModel() {
        viewModel.authResponse.observe(this) { response ->
            when (response.status) {
                ApiResponse.Status.LOADING -> {
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.text = "Processing..."
                }
                ApiResponse.Status.SUCCESS -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                ApiResponse.Status.ERROR -> {
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.text = if (isLoginMode) "Login" else "Register"
                    toast(response.message ?: "Error")
                    // Shake animation simulation would go here
                }
            }
        }
    }
}
