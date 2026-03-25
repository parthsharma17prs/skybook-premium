package com.skybook.local

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DataInitializer {
    fun seedData(context: Context) {
        val dao = AppDatabase.getDatabase(context).dao()
        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getFlightCount() < 10) {
                val airlines = listOf(
                    "Delta Airlines" to "delta_airline",
                    "United Airlines" to "united_airline",
                    "American Airlines" to "american_airline",
                    "Southwest" to "southwest_airline",
                    "Emirates" to "united_airline",
                    "Singapore Air" to "southwest_airline",
                    "British Airways" to "delta_airline",
                    "Qatar Airways" to "american_airline"
                )

                // Routes matching EXACTLY what users will type (city names shown in autocomplete)
                val routes = listOf(
                    Triple("New York", "JFK", Pair("Los Angeles", "LAX")),
                    Triple("Los Angeles", "LAX", Pair("New York", "JFK")),
                    Triple("London", "LHR", Pair("Paris", "CDG")),
                    Triple("Paris", "CDG", Pair("London", "LHR")),
                    Triple("Dubai", "DXB", Pair("Mumbai", "BOM")),
                    Triple("Mumbai", "BOM", Pair("Dubai", "DXB")),
                    Triple("Singapore", "SIN", Pair("Sydney", "SYD")),
                    Triple("Sydney", "SYD", Pair("Singapore", "SIN")),
                    Triple("Tokyo", "HND", Pair("San Francisco", "SFO")),
                    Triple("San Francisco", "SFO", Pair("Tokyo", "HND")),
                    Triple("New York", "JFK", Pair("London", "LHR")),
                    Triple("London", "LHR", Pair("Dubai", "DXB")),
                    Triple("Dubai", "DXB", Pair("Singapore", "SIN")),
                    Triple("Mumbai", "BOM", Pair("Singapore", "SIN")),
                    Triple("Bangkok", "BKK", Pair("Hong Kong", "HKG")),
                    Triple("Hong Kong", "HKG", Pair("Tokyo", "HND")),
                    Triple("Toronto", "YYZ", Pair("New York", "JFK")),
                    Triple("Chicago", "ORD", Pair("Los Angeles", "LAX")),
                    Triple("Amsterdam", "AMS", Pair("Dubai", "DXB")),
                    Triple("New York", "JFK", Pair("Paris", "CDG"))
                )

                val flights = mutableListOf<FlightEntity>()
                routes.forEach { (fromCity, fromCode, dest) ->
                    val (toCity, toCode) = dest
                    val baseDuration = "${(4..14).random()}h ${listOf(0,15,30,45).random()}m"
                    for (i in 1..12) {
                        val airline = airlines[i % airlines.size]
                        val hour = (5..21).random()
                        val arrHour = (hour + 4..hour + 14).random().coerceAtMost(24)
                        val price = (149..1200).random().toDouble()
                        val cls = if (i % 5 == 0) "Business" else if (i % 7 == 0) "First Class" else "Economy"
                        flights.add(
                            FlightEntity(
                                fromCity = fromCity,
                                fromCode = fromCode,
                                toCity = toCity,
                                toCode = toCode,
                                departureTime = "${hour.toString().padStart(2,'0')}:${listOf("00","15","30","45").random()}",
                                arrivalTime = "${(arrHour % 24).toString().padStart(2,'0')}:${listOf("00","15","30","45").random()}",
                                duration = baseDuration,
                                price = price,
                                airlineName = airline.first,
                                airlineLogo = airline.second,
                                classType = cls
                            )
                        )
                    }
                }
                dao.insertFlights(flights)
            }
        }
    }
}
