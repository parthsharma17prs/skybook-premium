package com.skybook.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Skyscanner API via RapidAPI.
 * Register at https://rapidapi.com/skyscanner/api/skyscanner44/
 * To get real time details, provide your RapidAPI Key.
 */
interface FlightApiService {
    @GET("flights/search-one-way")
    suspend fun searchFlights(
        @Query("fromId") from: String,
        @Query("toId") to: String,
        @Query("date") date: String,
        @Header("x-rapidapi-key") apiKey: String = "YOUR_RAPIDAPI_KEY_HERE",
        @Header("x-rapidapi-host") host: String = "skyscanner44.p.rapidapi.com"
    ): Response<FlightSearchResponse>
}

// Data models for Typical Flight Search API (as an example)
data class FlightSearchResponse(
    val status: Boolean,
    val data: FlightData?
)

data class FlightData(
    val itineraries: List<Itinerary>?
)

data class Itinerary(
    val id: String,
    val price: PriceDetails,
    val legs: List<Leg>
)

data class PriceDetails(
    val raw: Double,
    val formatted: String
)

data class Leg(
    val origin: Station,
    val destination: Station,
    val departure: String,
    val arrival: String,
    val durationInMinutes: Int,
    val carrier: Carrier
)

data class Station( val name: String, val displayCode: String )
data class Carrier( val name: String, val logoUrl: String )
