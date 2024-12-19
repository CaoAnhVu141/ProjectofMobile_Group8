package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var btnTroLai: Button
private lateinit var btnGuiYeuCau: Button
private lateinit var edtEmail: EditText
private lateinit var tvErrorMessage: TextView
private lateinit var databaseHelper: DatabaseHelper

class MainActivity_Forgot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot)
        setControl()
        setEvent()
        databaseHelper = DatabaseHelper(this) // Khởi tạo đối tượng DatabaseHelper
    }

    private fun setControl() {
        btnTroLai = findViewById(R.id.btnTroLai)
        btnGuiYeuCau = findViewById(R.id.btnGuiYeuCau)
        edtEmail = findViewById(R.id.editEmail)
        tvErrorMessage = findViewById(R.id.tvErrorMessage)
    }

    private fun setEvent() {
        btnTroLai.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }

        btnGuiYeuCau.setOnClickListener {
            val email = edtEmail.text.toString()

            if (isEmailValid(email) && isEmailExists(email)) {
                val intent = Intent(this, MainActivity_CaptCha::class.java)
                intent.putExtra("email", email) // Truyền email vào MainActivity_CaptCha
                startActivity(intent)
            } else {
                tvErrorMessage.text = "Email không tồn tại hoặc không hợp lệ"
                tvErrorMessage.visibility = TextView.VISIBLE
            }
        }
    }

    // Gọi phương thức kiểm tra email từ đối tượng DatabaseHelper
    private fun isEmailExists(email: String): Boolean {
        return databaseHelper.isEmailExists(email)
    }

    // Hàm Kiểm tra nhập có nhập gmail hợp lệ hay không
    private fun isEmailValid(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}