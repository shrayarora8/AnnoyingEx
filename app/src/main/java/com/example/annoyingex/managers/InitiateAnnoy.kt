package com.example.annoyingex.managers

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class InitiateAnnoy(private val context: Context) {

    companion object {
        const val WORK = "WORK"
    }

    private var workManager = WorkManager.getInstance(context)

    fun beginAnnoy() {
        if(isRunning()) {
            stopAnnoy()
        }
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = PeriodicWorkRequestBuilder<InitiateAnnoyWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(WORK)
            .build()
        workManager.enqueue(request)
    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(WORK).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopAnnoy() {
        workManager.cancelAllWorkByTag(WORK)
    }
}