package com.skybook.repository

import com.skybook.api.ApiService
import com.skybook.models.CreateBookingRequest

class BookingRepository(private val apiService: ApiService) {
    suspend fun createBooking(request: CreateBookingRequest) = apiService.createBooking(request)
    suspend fun getMyBookings() = apiService.getMyBookings()
    suspend fun getBoardingPass(ref: String) = apiService.getBoardingPass(ref)
}
