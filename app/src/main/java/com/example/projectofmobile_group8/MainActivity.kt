package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private lateinit var trove:Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.buy_items)
        setControl()
        setEvent()
    }

    private fun setControl(){
        trove = findViewById(R.id.btnDatHang)
    }

    private fun setEvent(){
        trove.setOnClickListener{
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }
    }
}