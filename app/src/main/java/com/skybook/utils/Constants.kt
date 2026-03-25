package com.skybook.utils

import com.skybook.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val SIGNALR_URL = "${BASE_URL}seathub"
    
    const val FLIGHT_SCHEDULED = "Scheduled"
    const val FLIGHT_DELAYED = "Delayed"
    const val FLIGHT_CANCELLED = "Cancelled"
    const val FLIGHT_BOARDING = "Boarding"
}
