package com.skybook.repository

import com.skybook.api.ApiService
import com.skybook.models.SeatLockRequest

class SeatRepository(private val apiService: ApiService) {
    suspend fun getSeatMap(flightId: Int) = apiService.getSeatMap(flightId)
    suspend fun lockSeat(request: SeatLockRequest) = apiService.lockSeat(request)
}
