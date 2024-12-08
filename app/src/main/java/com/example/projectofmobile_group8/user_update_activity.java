package com.example.projectofmobile_group8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class user_update_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_address);
        Button btnBacktobuyitems = findViewById(R.id.saveButton); // Nút chuyển qua màn hình 2
        btnBacktobuyitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView btnBacktobuyitems2 = findViewById(R.id.ivbacktobuyitem); // Nút chuyển qua màn hình 2
        btnBacktobuyitems2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}