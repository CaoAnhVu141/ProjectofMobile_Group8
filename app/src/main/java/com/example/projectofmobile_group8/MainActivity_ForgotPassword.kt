package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var edtPassword1: EditText
private lateinit var edtPassword2: EditText
private lateinit var btnConfirm: Button
private lateinit var databaseHelper: DatabaseHelper
private lateinit var email: String // Lưu email của người dùng

class MainActivity_ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.forgot_password_items)
        setControl()
        setEvent()
        // Lấy email từ intent nếu có (hoặc từ trước đó)
        email = intent.getStringExtra("email") ?: ""
        databaseHelper = DatabaseHelper(this)
    }

    private fun setControl() {
        edtPassword1 = findViewById(R.id.edtPassword1)
        edtPassword2 = findViewById(R.id.edtPassword2)
        btnConfirm = findViewById(R.id.btnConfirm)
    }

    private fun setEvent() {
        btnConfirm.setOnClickListener {
            val password1 =
                edtPassword1.text.toString().trim()  // Loại bỏ khoảng trắng ở đầu và cuối
            val password2 =
                edtPassword2.text.toString().trim()  // Loại bỏ khoảng trắng ở đầu và cuối

            // Kiểm tra nếu mật khẩu trống hoặc chứa khoảng trắng
            if (password1.isBlank() || password2.isBlank()) {
                showToast("Mật khẩu không được để trống hoặc chỉ chứa khoảng trắng!")
                return@setOnClickListener
            }
            // Kiểm tra độ dài mật khẩu phải >= 6 ký tự
            if (password1.length < 6 || password2.length < 6) {
                showToast("Mật khẩu phải có ít nhất 6 ký tự!")
                return@setOnClickListener
            }
            // Kiểm tra mật khẩu có khớp không
            if (password1 == password2) {
                // Cập nhật mật khẩu vào cơ sở dữ liệu
                val isUpdated = databaseHelper.updatePassword(email, password1)
                if (isUpdated) {
                    // Nếu cập nhật thành công, chuyển sang màn hình thông báo
                    val intent = Intent(this, MainActivity_Notification::class.java)
                    startActivity(intent)
                } else {
                    // Nếu không cập nhật được, hiển thị lỗi
                    showToast("Cập nhật mật khẩu thất bại!")
                }
            } else {
                showToast("Mật khẩu không khớp!")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}