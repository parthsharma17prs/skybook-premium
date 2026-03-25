package com.skybook.utils

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("skybook_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val USER_NAME = "user_name"
        private const val USER_ROLE = "user_role"
    }

    fun saveAuthData(token: String, name: String, role: String) {
        prefs.edit().apply {
            putString(ACCESS_TOKEN, token)
            putString(USER_NAME, name)
            putString(USER_ROLE, role)
            apply()
        }
    }

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, null)
    fun getUserName(): String? = prefs.getString(USER_NAME, "User")
    fun getUserRole(): String? = prefs.getString(USER_ROLE, "User")

    fun clear() {
        prefs.edit().clear().apply()
    }
}
