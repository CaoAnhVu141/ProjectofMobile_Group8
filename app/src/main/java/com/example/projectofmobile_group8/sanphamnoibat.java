package com.example.projectofmobile_group8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class sanphamnoibat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanphamnoibat);
        Intent intent = new Intent(sanphamnoibat.this, productdetail.class);
        Intent intent2 = new Intent(sanphamnoibat.this, shopping_cart.class);
        LinearLayout btnGotoDetail = findViewById(R.id.llSanpham1);
        btnGotoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        ImageView ivGotocart = findViewById(R.id.ivCart);
        ivGotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
        LinearLayout btnGotoDetail2 = findViewById(R.id.llSanpham2);
        btnGotoDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        LinearLayout btnGotoDetail3 = findViewById(R.id.llSanpham3);
        btnGotoDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        LinearLayout btnGotoDetail4 = findViewById(R.id.llSanpham4);
        btnGotoDetail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
