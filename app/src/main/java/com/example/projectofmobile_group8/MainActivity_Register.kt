package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


private lateinit var btnDangKy: Button
private lateinit var tvDangNhap: TextView
private lateinit var tvForgot: TextView

class MainActivity_Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        setControl()
        setEvent()
    }

    private fun setControl() {
        btnDangKy = findViewById(R.id.btnDangKy)
        tvDangNhap = findViewById(R.id.tvDangNhap)
        tvForgot = findViewById(R.id.tvForgot)
    }

    private fun setEvent() {
        tvDangNhap.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }
        tvForgot.setOnClickListener {
            val intent = Intent(this, MainActivity_Forgot::class.java)
            startActivity(intent)
        }
    }
}