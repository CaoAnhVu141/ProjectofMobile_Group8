package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var btn_purchase: TextView
class shopping_cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.shopping_cart)
        setControl()
        setEvent()
    }

    private fun setControl() {
        btn_purchase = findViewById(R.id.btn_purchase)
    }

    private fun setEvent() {
        btn_purchase.setOnClickListener{
            val intent = Intent(this, buy_items_activity::class.java)
            startActivity(intent)
        }
    }
}