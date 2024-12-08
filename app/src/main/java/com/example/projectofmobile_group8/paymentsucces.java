package com.example.projectofmobile_group8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class paymentsucces extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_success);

        Button btnbacktotranhgchu = findViewById(R.id.back);
        btnbacktotranhgchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(paymentsucces.this, sanphamnoibat.class);
                startActivity(intent);
            }
        });
    }
}
