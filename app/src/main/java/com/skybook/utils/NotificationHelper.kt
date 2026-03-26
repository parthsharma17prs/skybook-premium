package com.skybook.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.skybook.R
import com.skybook.activities.MainActivity

object NotificationHelper {
    private const val CHANNEL_ID = "booking_confirmations"
    private const val CHANNEL_NAME = "Booking Confirmations"
    private const val NOTIF_ID = 1001

    fun showBookingNotification(context: Context, destination: String, seats: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Shows notifications for confirmed flight bookings"
            }
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_plane) // update icon to existing one
            .setContentTitle("Booking Confirmed! ✈️")
            .setContentText("Your flight to $destination is booked. Seats: $seats")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(100, 200, 300, 400))

        try {
            NotificationManagerCompat.from(context).notify(NOTIF_ID, builder.build())
        } catch (e: SecurityException) {
            // Log it or handle Case where permission is missing (handled by system UI for app)
        }
    }
}
