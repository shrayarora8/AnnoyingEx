package com.example.annoyingex

import android.app.Application
import com.example.annoyingex.managers.InitiateAnnoy
import com.example.annoyingex.managers.JsonFetcher
import com.example.annoyingex.managers.NotifManager

class AnnoyingExApp: Application() {
    lateinit var jsonFetcher: JsonFetcher
        private set

    lateinit var initiateAnnoy: InitiateAnnoy
        private set

    lateinit var notifManager: NotifManager
        private set

    lateinit var currentText: String
        private set

    override fun onCreate() {
        super.onCreate()

        jsonFetcher = JsonFetcher(this)
        initiateAnnoy = InitiateAnnoy(this)
        notifManager = NotifManager(this)
        currentText = getString(R.string.error)
        jsonFetcher.getWeirdMessages({
            fetchWeirdTexts()
        })
    }

    fun fetchWeirdTexts() {
        jsonFetcher.allTexts?.let {
            currentText = it.random()
        }
    }
}