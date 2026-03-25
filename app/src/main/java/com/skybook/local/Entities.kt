package com.skybook.local

import androidx.room.*

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fromCity: String,
    val toCity: String,
    val fromCode: String,
    val toCode: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val price: Double,
    val airlineName: String,
    val airlineLogo: String, // String resource name
    val classType: String // Economy, Business
)

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val flightId: Int,
    val seatNumber: String,
    val bookingDate: String,
    val status: String // CONFIRMED, CANCELED
)
