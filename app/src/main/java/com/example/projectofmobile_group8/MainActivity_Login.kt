package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var editTaiKhoan: EditText
private lateinit var editPassWord: EditText
private lateinit var btnDangNhap: Button
private lateinit var tvDangKy: TextView
private lateinit var tvForgot: TextView


class MainActivity_Login : AppCompatActivity() {

    private lateinit var editTaiKhoan: EditText
    private lateinit var editPassWord: EditText
    private lateinit var btnDangNhap: Button
    private lateinit var tvDangKy: TextView
    private lateinit var tvForgot: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        setControl()
        setEvent()
        databaseHelper = DatabaseHelper(this) // Khởi tạo DatabaseHelper
    }

    private fun setControl() {
        editTaiKhoan = findViewById(R.id.editEmail)
        editPassWord = findViewById(R.id.editPassword)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        tvDangKy = findViewById(R.id.tvDangKy)
        tvForgot = findViewById(R.id.tvForgot)
    }

    private fun setEvent() {
        // Sự kiện Đăng Ký
        tvDangKy.setOnClickListener {
            val intent = Intent(this, MainActivity_Register::class.java)
            startActivity(intent)
        }
        // Sự kiện Quên mật khẩu
        tvForgot.setOnClickListener {
            val intent = Intent(this, MainActivity_Forgot::class.java)
            startActivity(intent)
        }
        // Sự kiện Đăng Nhập
        btnDangNhap.setOnClickListener {
            val email = editTaiKhoan.text.toString().trim()
            val password = editPassWord.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                // Kiểm tra thông tin đăng nhập
                val isValidLogin = databaseHelper.checkLogin(email, password)
                if (isValidLogin) {
                    // Đăng nhập thành công, chuyển màn hình
                    val intent = Intent(this, MainActivity_Shopping_Cart::class.java)
                    startActivity(intent)
                    finish() // Đóng màn hình đăng nhập
                } else {
                    // Thông báo lỗi đăng nhập
                    Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}