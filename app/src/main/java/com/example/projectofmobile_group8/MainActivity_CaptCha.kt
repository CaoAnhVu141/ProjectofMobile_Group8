package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var email: String
private lateinit var btnConfirm: Button

class MainActivity_CaptCha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.forgot_captcha_items)
        // Nhận email từ Intent
        email = intent.getStringExtra("email") ?: ""

        setControl()
        setEvent()
    }

    private fun setControl() {
        btnConfirm = findViewById(R.id.btnConfirm)
    }

    private fun setEvent() {
        btnConfirm.setOnClickListener {
            // Khi CAPTCHA hợp lệ, chuyển sang MainActivity_ForgotPassword và truyền email
            val intent = Intent(this, MainActivity_ForgotPassword::class.java)
            intent.putExtra("email", email) // Truyền email vào MainActivity_ForgotPassword
            startActivity(intent)
        }
    }
}