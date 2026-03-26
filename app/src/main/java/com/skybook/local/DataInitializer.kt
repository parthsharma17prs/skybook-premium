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

                // Professional Skyscanner-like Routes
                val routes = listOf(
                    Triple("New York", "JFK", Pair("London", "LHR")),
                    Triple("London", "LHR", Pair("Paris", "CDG")),
                    Triple("Dubai", "DXB", Pair("Mumbai", "BOM")),
                    Triple("Mumbai", "BOM", Pair("Dubai", "DXB")),
                    Triple("Singapore", "SIN", Pair("Sydney", "SYD")),
                    Triple("Tokyo", "HND", Pair("San Francisco", "SFO")),
                    Triple("Paris", "CDG", Pair("Dubai", "DXB")),
                    Triple("San Francisco", "SFO", Pair("Tokyo", "NRT")),
                    Triple("Delhi", "DEL", Pair("Bangalore", "BLR")),
                    Triple("Bangalore", "BLR", Pair("Delhi", "DEL")),
                    Triple("London", "LGW", Pair("Barcelona", "BCN")),
                    Triple("Toronto", "YYZ", Pair("Vancouver", "YVR")),
                    Triple("Sydney", "SYD", Pair("Auckland", "AKL")),
                    Triple("Chicago", "ORD", Pair("Los Angeles", "LAX")),
                    Triple("Miami", "MIA", Pair("Rio de Janeiro", "GIG")),
                    Triple("Berlin", "BER", Pair("Rome", "FCO")),
                    Triple("Singapore", "SIN", Pair("Bangkok", "BKK")),
                    Triple("Hong Kong", "HKG", Pair("Seoul", "ICN"))
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
