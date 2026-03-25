package com.skybook.models

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val role: String
)

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String
)

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ApiResponse<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    enum class Status { SUCCESS, ERROR, LOADING }
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(Status.SUCCESS, data)
        fun <T> error(msg: String): ApiResponse<T> = ApiResponse(Status.ERROR, message = msg)
        fun <T> loading(): ApiResponse<T> = ApiResponse(Status.LOADING)
    }
}
