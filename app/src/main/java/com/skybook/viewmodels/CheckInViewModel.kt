package com.skybook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybook.models.ApiResponse
import com.skybook.models.BoardingPass
import com.skybook.repository.BookingRepository
import kotlinx.coroutines.launch

class CheckInViewModel(private val repository: BookingRepository) : ViewModel() {
    val boardingPass = MutableLiveData<ApiResponse<BoardingPass>>()

    fun fetchBoardingPass(ref: String) {
        boardingPass.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.getBoardingPass(ref)
                if (response.isSuccessful) {
                    boardingPass.postValue(ApiResponse.success(response.body()!!))
                } else {
                    boardingPass.postValue(ApiResponse.error("Check-in not available"))
                }
            } catch (e: Exception) {
                boardingPass.postValue(ApiResponse.error("Network error"))
            }
        }
    }
}
