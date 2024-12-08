package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var btnSave: Button
private lateinit var btnCancle: Button

class user_update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.user_update)
        setControl()
        setEvent()
    }

    private fun setControl() {
        btnSave = findViewById(R.id.btnSave)
        btnCancle = findViewById(R.id.btnCancel)
    }

    private fun setEvent() {

        btnSave.setOnClickListener {
            btnSave.setOnClickListener {
                finish()
            }
        }
        btnCancle.setOnClickListener {
            btnCancle.setOnClickListener {
                finish()
            }
        }
    }
}