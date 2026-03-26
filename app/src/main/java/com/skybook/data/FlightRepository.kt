package com.skybook.data

import android.content.Context
import com.skybook.data.remote.FlightApiService
import com.skybook.local.AppDatabase
import com.skybook.local.FlightEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightRepository(private val context: Context) {
    private val dao = AppDatabase.getDatabase(context).dao()
    
    // Remote API Setup (Skyscanner via RapidAPI)
    // To make this work, the user needs to provide a RapidAPI Key
    private val api: FlightApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://skyscanner44.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlightApiService::class.java)
    }

    // Local DB - Reliable & always works
    suspend fun getLocalFlights(from: String, to: String): List<FlightEntity> {
        return dao.searchFlights(from, to)
    }

    // For Home Page - Recent / Recommended Flights
    suspend fun getRecommendedFlights(): List<FlightEntity> {
        return dao.searchFlights("New York", "London").take(5) + 
               dao.searchFlights("Dubai", "Mumbai").take(5)
    }

    /**
     * Integrate Real-time API here by calling the remote service.
     * Mappings from Remote Model to our FlightEntity are needed here.
     */
    suspend fun searchRealTimeFlights(from: String, to: String, date: String): List<FlightEntity> {
        // By default, try to get local flights. If you have an API key, call the remote API.
        val searchResults = getLocalFlights(from, to)
        return if (searchResults.isNotEmpty()) {
            searchResults
        } else {
            // Future: integrate the API call here if a key is provided
            // val remoteResponse = api.searchFlights(from, to, date)
            // if (remoteResponse.isSuccessful) mapToEntities(remoteResponse.body()) else ...
            emptyList()
        }
    }
}
