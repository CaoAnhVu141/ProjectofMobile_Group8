package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private lateinit var btnTroLai: Button
private lateinit var btnGuiYeuCau: Button


class MainActivity_Forgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot)
        setControl()
        setEvent()
    }

    private fun setControl() {
        btnTroLai = findViewById(R.id.btnTroLai)
        btnGuiYeuCau = findViewById(R.id.btnGuiYeuCau)

    }
    private fun setEvent() {
        btnTroLai.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }
        btnGuiYeuCau.setOnClickListener{
            val intent = Intent(this, MainActivity_ForgotPassword::class.java)
            startActivity(intent)
        }
    }
}