package com.skybook.api

import com.skybook.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("flights/search")
    suspend fun searchFlights(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("date") date: String,
        @Query("passengers") passengers: Int,
        @Query("class") cabinClass: String
    ): Response<List<Flight>>

    @GET("seats/{flightId}/map")
    suspend fun getSeatMap(@Path("flightId") flightId: Int): Response<List<Seat>>

    @POST("seats/lock")
    suspend fun lockSeat(@Body request: SeatLockRequest): Response<Void>

    @POST("bookings")
    suspend fun createBooking(@Body request: CreateBookingRequest): Response<BookingResponse>

    @GET("bookings/my")
    suspend fun getMyBookings(): Response<List<BookingResponse>>

    @POST("checkin/{ref}")
    suspend fun checkIn(@Path("ref") ref: String): Response<BoardingPass>

    @GET("checkin/{ref}/boardingpass")
    suspend fun getBoardingPass(@Path("ref") ref: String): Response<BoardingPass>

    @GET("recommendations")
    suspend fun getRecommendations(): Response<List<Flight>>

    @GET("flightstatus/{number}")
    suspend fun getFlightStatus(@Path("number") number: String, @Query("date") date: String): Response<FlightStatus>
}
