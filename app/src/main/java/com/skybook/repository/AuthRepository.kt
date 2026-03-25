package com.skybook.repository

import com.skybook.api.ApiService
import com.skybook.models.*

class AuthRepository(private val apiService: ApiService) {
    suspend fun login(request: LoginRequest) = apiService.login(request)
    suspend fun register(request: RegisterRequest) = apiService.register(request)
}
