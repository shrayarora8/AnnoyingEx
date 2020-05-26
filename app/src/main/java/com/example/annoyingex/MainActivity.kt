package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annoyingex.managers.InitiateAnnoy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var initiateAnnoyManager: InitiateAnnoy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initiateAnnoyManager = (application as AnnoyingExApp).initiateAnnoy
        btnSlideInDM.setOnClickListener {
            initiateAnnoyManager.beginAnnoy()

        }

        btnGiveClosure.setOnClickListener {
            initiateAnnoyManager.stopAnnoy()
        }
    }
}
