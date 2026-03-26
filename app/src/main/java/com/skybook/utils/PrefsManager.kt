package com.skybook.utils

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("skybook_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID    = "user_id"
        private const val USER_NAME  = "user_name"
        private const val USER_EMAIL = "user_email"
    }

    fun saveAuthData(id: Int, name: String, email: String) {
        prefs.edit()
            .putInt(USER_ID, id)
            .putString(USER_NAME, name)
            .putString(USER_EMAIL, email)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getInt(USER_ID, -1) != -1
    fun getUserId(): Int = prefs.getInt(USER_ID, -1)
    fun getUserName(): String? = prefs.getString(USER_NAME, "Traveller")
    fun getUserEmail(): String? = prefs.getString(USER_EMAIL, "user@skybook.app")

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
