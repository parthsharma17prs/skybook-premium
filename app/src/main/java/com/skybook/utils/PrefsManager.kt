package com.skybook.utils

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("skybook_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val USER_NAME = "user_name"
        private const val USER_ROLE = "user_role"
        private const val USER_ID = "user_id"
    }

    fun saveAuthData(id: Int, name: String, email: String) {
        prefs.edit().apply {
            putInt(USER_ID, id)
            putString(USER_NAME, name)
            putString(ACCESS_TOKEN, email) // using email as a token marker
            apply()
        }
    }

    fun isLoggedIn(): Boolean = prefs.getInt(USER_ID, -1) != -1
    fun getUserId(): Int = prefs.getInt(USER_ID, -1)
    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, null)
    fun getUserName(): String? = prefs.getString(USER_NAME, "User")
    fun getUserEmail(): String? = prefs.getString(ACCESS_TOKEN, "alex.johnson@example.com")
    fun getUserRole(): String? = prefs.getString(USER_ROLE, "User")

    fun clear() {
        prefs.edit().clear().apply()
    }
}
