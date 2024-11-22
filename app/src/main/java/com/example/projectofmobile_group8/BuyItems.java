package com.example.projectofmobile_group8;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class BuyItems  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_items); // Gắn file XML cho màn hình 1

        Button btnGoToSecond = findViewById(R.id.btn_order); // Nút chuyển qua màn hình 2
        btnGoToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển tới SecondActivity
                Intent intent = new Intent(BuyItems.this, PaymentSuccess.class);
                startActivity(intent);
            }
        });
    }
}
