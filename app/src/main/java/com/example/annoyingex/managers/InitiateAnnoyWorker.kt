package com.example.annoyingex.managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.annoyingex.AnnoyingExApp

class InitiateAnnoyWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    override fun doWork(): Result {
        val notificationManager = (applicationContext as AnnoyingExApp).notifManager
        notificationManager.sendCreepyNotification()
        return Result.success()
    }
}