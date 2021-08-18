package com.example.golftracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Welcome : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)

        startButton.setOnClickListener {
            val intent = Intent(this@Welcome, QuantitySelection::class.java)
            startActivity(intent)
        }
    }
}