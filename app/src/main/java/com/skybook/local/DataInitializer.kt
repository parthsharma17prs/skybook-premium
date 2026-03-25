package com.skybook.local

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DataInitializer {
    fun seedData(context: Context) {
        val dao = AppDatabase.getDatabase(context).dao()
        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getFlightCount() < 50) {
                val routes = listOf(
                    Route("New York", "JFK", "Los Angeles", "LAX"),
                    Route("London", "LHR", "Paris", "CDG"),
                    Route("Dubai", "DXB", "Mumbai", "BOM"),
                    Route("Singapore", "SIN", "Sydney", "SYD"),
                    Route("Tokyo", "HND", "San Francisco", "SFO"),
                    Route("Los Angeles", "LAX", "New York", "JFK"),
                    Route("Mumbai", "BOM", "London", "LHR")
                )

                val airlines = listOf(
                    "Delta Air" to "delta_airline",
                    "United Air" to "united_airline",
                    "American Air" to "american_airline",
                    "Southwest" to "southwest_airline",
                    "Emirates" to "united_airline", // Placeholder logo
                    "Qatar Airways" to "delta_airline", // Placeholder
                    "British Airways" to "united_airline" // Placeholder
                )

                val flights = mutableListOf<FlightEntity>()
                routes.forEach { route ->
                    for (i in 1..15) {
                        val airline = airlines.random()
                        flights.add(
                            FlightEntity(
                                fromCity = route.fromCity,
                                fromCode = route.fromCode,
                                toCity = route.toCity,
                                toCode = route.toCode,
                                departureTime = "${(8..22).random()}:${(10..55).random()}",
                                arrivalTime = "${(10..24).random()}:${(10..55).random()}",
                                duration = "6h 30m",
                                price = (180..850).random().toDouble(),
                                airlineName = airline.first,
                                airlineLogo = airline.second,
                                classType = if (i % 4 == 0) "Business" else "Economy"
                            )
                        )
                    }
                }
                dao.insertFlights(flights)
            }
        }
    }

    private data class Route(val fromCity: String, val fromCode: String, val toCity: String, val toCode: String)
}
