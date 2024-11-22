package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var btnConfirmSuccess:Button

class MainActivity_Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.notification_items)

        setControl()
        setEvent()
    }

    private fun setControl() {
        btnConfirmSuccess = findViewById(R.id.btnConfirmSuccess)
    }

    private fun setEvent() {
        btnConfirmSuccess.setOnClickListener{
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }
    }
}