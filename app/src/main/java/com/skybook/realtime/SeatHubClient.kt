package com.skybook.realtime

import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.skybook.utils.Constants
import kotlinx.coroutines.flow.MutableSharedFlow

class SeatHubClient {
    private var connection: HubConnection = HubConnectionBuilder
        .create(Constants.SIGNALR_URL)
        .build()

    val seatUpdates = MutableSharedFlow<Pair<Int, String>>(extraBufferCapacity = 1)

    fun start() {
        connection.on("SeatStatusUpdated", { flightId: Int, seatNum: String ->
            seatUpdates.tryEmit(Pair(flightId, seatNum))
        }, Int::class.java, String::class.java)

        connection.start().blockingAwait()
    }

    fun stop() {
        connection.stop()
    }
}
