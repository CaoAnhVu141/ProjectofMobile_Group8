package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var btnAddUser: Button
private lateinit var btnEditUser1: Button
private lateinit var btnEditUser2: Button
private lateinit var btnEditUser3: Button

class crud_user : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crud_user)
        setControl()
        setEvent()
    }

    private fun setControl() {
        btnAddUser = findViewById(R.id.btnAddUser)
        btnEditUser1 = findViewById(R.id.btnEditUser1)
        btnEditUser2 = findViewById(R.id.btnEditUser2)
        btnEditUser3 = findViewById(R.id.btnEditUser3)
    }

    private fun setEvent() {

        btnAddUser.setOnClickListener {
            btnAddUser.setOnClickListener {
                val intent = Intent(this, add_user::class.java)
                startActivity(intent)
            }
        }
        btnEditUser1.setOnClickListener {
            btnEditUser1.setOnClickListener {
                val intent = Intent(this, user_update::class.java)
                startActivity(intent)
            }
        }
        btnEditUser2.setOnClickListener {
            btnEditUser2.setOnClickListener {
                val intent = Intent(this, user_update::class.java)
                startActivity(intent)
            }
        }
        btnEditUser3.setOnClickListener {
            btnEditUser3.setOnClickListener {
                val intent = Intent(this, user_update::class.java)
                startActivity(intent)
            }
        }
    }
}