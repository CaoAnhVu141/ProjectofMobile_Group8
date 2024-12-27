package com.example.projectofmobile_group8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity_Shopping_Cart : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnPurchase: Button
    private lateinit var productAdapter: ProductAdapter
    private lateinit var imgtrove: ImageView

    // Danh sách sản phẩm trong giỏ hàng
    private val productList = arrayListOf(
        Product("Nike Air Force 1 Low", "2.500.000 vnd", 1, 2500000),
        Product("Adidas UltraBoost", "3.200.000 vnd", 1, 3200000),
        Product("Puma RS-X", "1.800.000 vnd", 1, 1800000),
        Product("Nike Air Force 1 Low", "2.500.000 vnd", 1, 2500000),
        Product("Adidas UltraBoost", "3.200.000 vnd", 1, 3200000),
        Product("Puma RS-X", "1.800.000 vnd", 1, 1800000),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_cart)
        setControl()
        setEvent()
    }

    private fun setControl() {
        listView = findViewById(R.id.lv_products)
        tvTotalPrice = findViewById(R.id.tv_total_price)
        btnPurchase = findViewById(R.id.btn_purchase)
        imgtrove = findViewById(R.id.imgtrove)
        // Thiết lập Adapter
        productAdapter = ProductAdapter(
            this,
            productList,
            onQuantityChange = { updateTotalPrice() },
            showButtons = true
        )
        //Hiển thị dữ liệu lên
        listView.adapter = productAdapter
    }

    private fun setEvent() {

        imgtrove.setOnClickListener {
            val intent = Intent(this, ProductList::class.java)
            startActivity(intent)
        }
        // Xử lý sự kiện khi nhấn nút "Mua hàng"
        btnPurchase.setOnClickListener {
            if (productList.isNotEmpty()) {
                val intent = Intent(this, PaymentActivity::class.java)

                // Truyền danh sách sản phẩm và tổng tiền qua trang mua hàng
                intent.putParcelableArrayListExtra("productList", productList)
                intent.putExtra("totalPrice", calculateTotalPrice())

                startActivity(intent)
            } else {
                Toast.makeText(this, "Giỏ hàng của bạn đang trống!", Toast.LENGTH_SHORT).show()
            }
        }
        updateTotalPrice()
    }

    // Hàm cập nhật tổng tiền
    private fun updateTotalPrice() {
        val totalPrice = calculateTotalPrice()
        tvTotalPrice.text = "$totalPrice vnd"
    }

    // Hàm tính tổng tiền
    private fun calculateTotalPrice(): Int {
        return productList.sumOf { it.quantity * it.priceInNumber }
    }
}
