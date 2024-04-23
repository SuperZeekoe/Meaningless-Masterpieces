package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnStartCreating)
        button.setOnClickListener {
            // Navigate to MainActivity2 when the button is clicked
            startCreating()
        }
    }

    private fun startCreating() {
        // Create an Intent to start MainActivity2
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}