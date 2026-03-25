package com.skybook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybook.models.ApiResponse
import com.skybook.models.BookingResponse
import com.skybook.models.CreateBookingRequest
import com.skybook.repository.BookingRepository
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository) : ViewModel() {
    val bookingResult = MutableLiveData<ApiResponse<BookingResponse>>()
    val userBookings = MutableLiveData<ApiResponse<List<BookingResponse>>>()

    fun createBooking(request: CreateBookingRequest) {
        bookingResult.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.createBooking(request)
                if (response.isSuccessful) {
                    bookingResult.postValue(ApiResponse.success(response.body()!!))
                } else {
                    bookingResult.postValue(ApiResponse.error("Booking failed"))
                }
            } catch (e: Exception) {
                bookingResult.postValue(ApiResponse.error("Network error"))
            }
        }
    }

    fun getMyBookings() {
        userBookings.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.getMyBookings()
                if (response.isSuccessful) {
                    userBookings.postValue(ApiResponse.success(response.body()!!))
                } else {
                    userBookings.postValue(ApiResponse.error("Failed to load bookings"))
                }
            } catch (e: Exception) {
                userBookings.postValue(ApiResponse.error("Network error"))
            }
        }
    }
}
