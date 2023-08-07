package com.example.composeplayground.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.composeplayground.MainActivity
import com.example.composeplayground.R
import kotlin.random.Random

object Notification {

    private var manager: NotificationManager? = null
    private const val channelId = "playground_channel_id"

    fun create(applicationContext: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    channelId,
                    "Channel playground",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Channel"
                }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            manager = notificationManager


        }
    }

    fun notify(applicationContext: Context) {
        val links = listOf("Support", "Ticket")
        val description = listOf("😌", "😊", "🤩", "🧐", "🥸")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager != null) {
            links.forEach { link ->
//                val intent = Intent(applicationContext, MainActivity::class.java).apply {
//                    putExtra("deepLink", "ninjarmm://assist/support")
//                }

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("ninjarmm://assist/support"),
                )

                val pendingIntent = PendingIntent.getActivity(
                    applicationContext,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT // TODO
                )

                val builder = NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(link)
                    .setContentText(description[Random.nextInt(0, description.size.dec())])
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.frame_598)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

                manager?.notify(Random.nextInt(Int.MAX_VALUE), builder.build())
            }
        }
    }

}
