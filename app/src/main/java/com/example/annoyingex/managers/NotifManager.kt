package com.example.annoyingex.managers
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import com.example.annoyingex.AnnoyingExApp
import com.example.annoyingex.MainActivity
import com.example.annoyingex.R
import kotlin.random.Random

class NotifManager (private val context: Context) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createChannel()
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
        const val NOTIFICATION_ID = "NOTIFICATION_ID"
    }

    fun sendCreepyNotification() {
        val app = (context as AnnoyingExApp)
        app.fetchWeirdTexts()
        val content = app.currentText
        val dealsIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(NOTIFICATION_ID, content)
        }

        val pendIntent = PendingIntent.getActivity(context, Random.nextInt(), dealsIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("OMG not you again")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notifications from ex"
            val descriptionText = "Omg not again why did I date this person"
            val importance = IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }
}