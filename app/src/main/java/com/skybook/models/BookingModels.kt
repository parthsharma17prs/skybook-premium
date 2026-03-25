package com.skybook.models

data class CreateBookingRequest(
    val flightId: Int,
    val seatId: Int,
    val passengerFirstName: String,
    val passengerLastName: String,
    val passengerEmail: String,
    val passengerPhone: String,
    val paymentMethod: String,
    val baggageExtraKg: Int,
    val mealPreference: String?
)

data class BookingResponse(
    val bookingRef: String,
    val flightNumber: String,
    val source: String,
    val destination: String,
    val passengerName: String,
    val seatNumber: String,
    val status: String,
    val totalAmount: Double,
    val bookedAt: String
)

data class BoardingPass(
    val bookingRef: String,
    val passengerName: String,
    val flightNumber: String,
    val gate: String,
    val boardingTime: String,
    val seatNumber: String,
    val barcodeData: String
)

data class FlightStatus(
    val flightId: Int,
    val oldStatus: String,
    val newStatus: String,
    val reason: String?,
    val delayMinutes: Int,
    val updatedAt: String
)
