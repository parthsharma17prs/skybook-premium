package com.skybook.models

data class Flight(
    val id: Int,
    val flightNumber: String,
    val airline: String,
    val airlineLogoUrl: String?,
    val sourceIata: String,
    val destinationIata: String,
    val departureTime: String,
    val arrivalTime: String,
    val basePrice: Double,
    val currentPrice: Double,
    val availableSeats: Int,
    val aircraftType: String,
    val status: String?
)

data class Seat(
    val id: Int,
    val seatNumber: String,
    val seatClass: String,
    val isAvailable: Boolean,
    val isWindow: Boolean,
    val isAisle: Boolean,
    val extraLegroom: Boolean,
    val isLocked: Boolean
)

data class SeatLockRequest(
    val flightId: Int,
    val seatNumber: String
)
