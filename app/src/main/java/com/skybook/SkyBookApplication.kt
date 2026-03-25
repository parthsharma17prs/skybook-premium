package com.skybook

import android.app.Application
import com.skybook.api.ApiClient
import com.skybook.api.AuthInterceptor
import com.skybook.repository.*
import com.skybook.utils.PrefsManager

class SkyBookApplication : Application() {
    
    // Dependency Container (Manual DI)
    lateinit var prefsManager: PrefsManager
    lateinit var authRepository: AuthRepository
    lateinit var flightRepository: FlightRepository
    lateinit var bookingRepository: BookingRepository
    lateinit var seatRepository: SeatRepository

    override fun onCreate() {
        super.onCreate()
        
        prefsManager = PrefsManager(this)
        val apiService = ApiClient.getClient(AuthInterceptor(prefsManager))
        
        authRepository = AuthRepository(apiService)
        flightRepository = FlightRepository(apiService)
        bookingRepository = BookingRepository(apiService)
        seatRepository = SeatRepository(apiService)
    }
}
