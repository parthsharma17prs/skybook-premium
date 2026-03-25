package com.skybook.api

import com.skybook.utils.PrefsManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val prefsManager: PrefsManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = prefsManager.getAccessToken()
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}
