package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.SQLiteDatabase

class MainActivity_Register : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSDT: EditText
    private lateinit var editHoTen: EditText
    private lateinit var editPassword1: EditText
    private lateinit var editPassword2: EditText
    private lateinit var btnDangKy: Button
    private lateinit var tvDangNhap: TextView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setControl()
        setEvent()
    }

    private fun setControl() {
        editEmail = findViewById(R.id.editEmail)
        editSDT = findViewById(R.id.editSDT)
        editHoTen = findViewById(R.id.editHoTen)
        editPassword1 = findViewById(R.id.editPassword1)
        editPassword2 = findViewById(R.id.editPassword2)
        btnDangKy = findViewById(R.id.btnDangKy)
        tvDangNhap = findViewById(R.id.tvDangNhap)

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
    }

    private fun setEvent() {
        // Handle button click for registration
        btnDangKy.setOnClickListener {
            val email = editEmail.text.toString()
            val phone = editSDT.text.toString()
            val name = editHoTen.text.toString()
            val password1 = editPassword1.text.toString()
            val password2 = editPassword2.text.toString()

            // Check if all fields are filled
            if (email.isEmpty() || phone.isEmpty() || name.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if passwords match
            if (password1 != password2) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if email already exists
            if (isEmailExist(email)) {
                Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Insert data into database with id_role set to 2
            val insertQuery = "INSERT INTO user (email, phone, name, password, id_role) VALUES ('$email', '$phone', '$name', '$password1', 2)"
            db.execSQL(insertQuery)

            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()

            // Redirect to Login screen
            val intent = Intent(this, MainActivity_Register::class.java)
            startActivity(intent)
            finish()
        }

        // Handle redirect to login screen
        tvDangNhap.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Check if email exists in the database
    private fun isEmailExist(email: String): Boolean {
        val query = "SELECT * FROM user WHERE email = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
