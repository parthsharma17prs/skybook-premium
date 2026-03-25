package com.skybook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybook.models.ApiResponse
import com.skybook.models.Seat
import com.skybook.models.SeatLockRequest
import com.skybook.repository.SeatRepository
import kotlinx.coroutines.launch

class SeatViewModel(private val repository: SeatRepository) : ViewModel() {
    val seatMap = MutableLiveData<ApiResponse<List<Seat>>>()
    val lockStatus = MutableLiveData<ApiResponse<Void>>()

    fun fetchSeatMap(flightId: Int) {
        seatMap.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.getSeatMap(flightId)
                if (response.isSuccessful) {
                    seatMap.postValue(ApiResponse.success(response.body()!!))
                } else {
                    seatMap.postValue(ApiResponse.error("Failed to load seats"))
                }
            } catch (e: Exception) {
                seatMap.postValue(ApiResponse.error(e.message ?: "Network error"))
            }
        }
    }

    fun lockSeat(flightId: Int, seatNumber: String) {
        viewModelScope.launch {
            try {
                val response = repository.lockSeat(SeatLockRequest(flightId, seatNumber))
                if (response.isSuccessful) {
                    lockStatus.postValue(ApiResponse.success(null!!))
                } else {
                    lockStatus.postValue(ApiResponse.error("Seat already locked"))
                }
            } catch (e: Exception) {
                lockStatus.postValue(ApiResponse.error("Error locking seat"))
            }
        }
    }
}
