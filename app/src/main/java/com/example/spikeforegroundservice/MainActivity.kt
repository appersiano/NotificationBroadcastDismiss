package com.example.spikeforegroundservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(
                Intent(
                        applicationContext,
                        MyForegroundService::class.java
                )
        )
    }
}