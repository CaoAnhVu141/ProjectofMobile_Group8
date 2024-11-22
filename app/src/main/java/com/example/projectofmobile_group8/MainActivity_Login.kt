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

private lateinit var editTaiKhoan:EditText
private lateinit var editPassWord:EditText
private lateinit var btnDangNhap:Button
private lateinit var tvDangKy:TextView
private lateinit var tvForgot:TextView


class MainActivity_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        setControl()
        setEvent()
    }

    private fun setControl()
    {
        editTaiKhoan = findViewById(R.id.editEmail)
        editPassWord = findViewById(R.id.editPassword)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        tvDangKy = findViewById(R.id.tvDangKy)
        tvForgot = findViewById(R.id.tvForgot)
    }

    private fun setEvent() {
        tvDangKy.setOnClickListener {
            val intent = Intent(this, MainActivity_Register::class.java)
            startActivity(intent)
        }
        tvForgot.setOnClickListener {
            val intent = Intent(this, MainActivity_Forgot::class.java)
            startActivity(intent)
        }
    }
}