package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.skybook.databinding.ActivityAuthBinding
import com.skybook.local.AppDatabase
import com.skybook.local.UserEntity
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = PrefsManager(this)
        val dao = AppDatabase.getDatabase(this).dao()

        binding.tvToggleAuth.setOnClickListener {
            isLoginMode = !isLoginMode
            updateUi()
        }

        binding.btnAuthAction.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                if (isLoginMode) {
                    val user = dao.login(email, pass)
                    if (user != null) {
                        prefs.saveAuthData(user.id, user.name, user.email)
                        startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@AuthActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (name.isEmpty()) {
                        Toast.makeText(this@AuthActivity, "Name required", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    try {
                        val newUserId = dao.registerUser(UserEntity(name = name, email = email, password = pass))
                        prefs.saveAuthData(newUserId.toInt(), name, email)
                        startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@AuthActivity, "Email already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun updateUi() {
        if (isLoginMode) {
            binding.tvAuthTitle.text = "Welcome\nBack!"
            binding.tvAuthSubtitle.text = "Login to continue your journey"
            binding.etName.visibility = View.GONE
            binding.tvAuthBtn.text = "Login"
            binding.tvToggleAuth.text = "Don't have an account? Sign Up"
        } else {
            binding.tvAuthTitle.text = "Get\nStarted!"
            binding.tvAuthSubtitle.text = "Create an account to track bookings"
            binding.etName.visibility = View.VISIBLE
            binding.tvAuthBtn.text = "Sign Up"
            binding.tvToggleAuth.text = "Already have an account? Login"
        }
    }
}
