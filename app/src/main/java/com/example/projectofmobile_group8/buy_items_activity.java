package com.example.projectofmobile_group8;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectofmobile_group8.R;

public class buy_items_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_items); // Gắn file XML cho màn hình 1

        Button btnGoToSecond = findViewById(R.id.btnOrder); // Nút chuyển qua màn hình 2
        btnGoToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buy_items_activity.this, paymentsucces.class);
                startActivity(intent);
            }
        });

        LinearLayout llGotoPayment = findViewById(R.id.llattribute); // Nút chuyển qua màn hình 2
        llGotoPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buy_items_activity.this, payment_activity.class);
                startActivity(intent);
            }
        });

        LinearLayout llGotoUserUpdate = findViewById(R.id.llusserinfo); // Nút chuyển qua màn hình 2
        llGotoUserUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buy_items_activity.this, user_update_activity.class);
                startActivity(intent);
            }
        });
    }
}
