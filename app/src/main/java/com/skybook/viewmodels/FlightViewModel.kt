package com.skybook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybook.models.ApiResponse
import com.skybook.models.Flight
import com.skybook.repository.FlightRepository
import kotlinx.coroutines.launch

class FlightViewModel(private val repository: FlightRepository) : ViewModel() {
    val searchResults = MutableLiveData<ApiResponse<List<Flight>>>()
    val recommendations = MutableLiveData<ApiResponse<List<Flight>>>()

    fun searchFlights(from: String, to: String, date: String, passengers: Int, cabinClass: String) {
        searchResults.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.searchFlights(from, to, date, passengers, cabinClass)
                if (response.isSuccessful) {
                    searchResults.postValue(ApiResponse.success(response.body()!!))
                } else {
                    searchResults.postValue(ApiResponse.error("No flights found"))
                }
            } catch (e: Exception) {
                searchResults.postValue(ApiResponse.error(e.message ?: "Network error"))
            }
        }
    }

    fun getRecommendations() {
        recommendations.value = ApiResponse.loading()
        viewModelScope.launch {
            try {
                val response = repository.getRecommendations()
                if (response.isSuccessful) {
                    recommendations.postValue(ApiResponse.success(response.body()!!))
                } else {
                    recommendations.postValue(ApiResponse.error("Failed to load recommendations"))
                }
            } catch (e: Exception) {
                recommendations.postValue(ApiResponse.error(e.message ?: "Network error"))
            }
        }
    }
}
