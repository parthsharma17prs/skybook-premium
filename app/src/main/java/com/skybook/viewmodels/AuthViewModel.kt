package com.skybook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybook.models.*
import com.skybook.repository.AuthRepository
import com.skybook.utils.PrefsManager
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository, private val prefs: PrefsManager) : ViewModel() {
    val authResponse = MutableLiveData<ApiResponse<AuthResponse>>()

    fun login(request: LoginRequest) {
        authResponse.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.login(request)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    prefs.saveAuthData(body.accessToken, body.firstName, body.role)
                    authResponse.postValue(ApiResponse.success(body))
                } else {
                    authResponse.postValue(ApiResponse.error("Login failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                authResponse.postValue(ApiResponse.error(e.message ?: "Network error"))
            }
        }
    }

    fun register(request: RegisterRequest) {
        authResponse.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.register(request)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    prefs.saveAuthData(body.accessToken, body.firstName, body.role)
                    authResponse.postValue(ApiResponse.success(body))
                } else {
                    authResponse.postValue(ApiResponse.error("Registration failed"))
                }
            } catch (e: Exception) {
                authResponse.postValue(ApiResponse.error(e.message ?: "Network error"))
            }
        }
    }
}
