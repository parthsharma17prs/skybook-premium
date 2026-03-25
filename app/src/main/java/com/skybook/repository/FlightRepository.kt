package com.skybook.repository

import com.skybook.api.ApiService
import com.skybook.models.Flight

class FlightRepository(private val apiService: ApiService) {
    suspend fun searchFlights(from: String, to: String, date: String, passengers: Int, cabinClass: String) =
        apiService.searchFlights(from, to, date, passengers, cabinClass)
    
    suspend fun getRecommendations() = apiService.getRecommendations()
    
    suspend fun getFlightStatus(number: String, date: String) = apiService.getFlightStatus(number, date)
}
