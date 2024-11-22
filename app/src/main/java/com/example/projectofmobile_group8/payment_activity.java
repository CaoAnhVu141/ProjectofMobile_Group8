package com.example.projectofmobile_group8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class payment_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        Button btnBacktobuyitems = findViewById(R.id.btnXacnhan); // Nút chuyển qua màn hình 2
        btnBacktobuyitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView btnBacktobuyitems2 = findViewById(R.id.imgvBack); // Nút chuyển qua màn hình 2
        btnBacktobuyitems2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
