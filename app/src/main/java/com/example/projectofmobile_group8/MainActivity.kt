package com.example.projectofmobile_group8

import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnPurchase: Button
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    private val productDao by lazy { (applicationContext as MyApp).database.productDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_cart)

        setControl()
        loadProducts()
        setEvent()
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.rv_products)
        tvTotalPrice = findViewById(R.id.tv_total_price)
        btnPurchase = findViewById(R.id.btn_purchase)

        productAdapter = ProductAdapter(
            context = this,
            productList = productList,
            onQuantityChange = { updateTotalPrice() },
            showButtons = true
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter
    }

    private fun setEvent() {
        btnPurchase.setOnClickListener {
            if (productList.isNotEmpty()) {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putParcelableArrayListExtra("productList", ArrayList(productList))
                intent.putExtra("totalPrice", calculateTotalPrice())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Giỏ hàng của bạn đang trống!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            val productCount = productDao.getProductCount() // Kiểm tra xem có dữ liệu không
            if (productCount == 0) {
                addSampleData() // Thêm dữ liệu mẫu
            }

            val products = productDao.getAllProducts() // Lấy danh sách sản phẩm
            productList.clear()
            productList.addAll(products)
            productAdapter.updateProductList(productList)
            updateTotalPrice()
        }
    }

    //hàm nạp dữ liệu vào
//    private suspend fun addSampleData() {
//        val sampleProducts = listOf(
//            Product(name = "Sản phẩm A", price = 100.0, quantity = 1, priceInNumber = 100),
//            Product(name = "Sản phẩm B", price = 200.0, quantity = 2, priceInNumber = 200),
//            Product(name = "Sản phẩm C", price = 300.0, quantity = 3, priceInNumber = 300)
//        )
//        productDao.insertProducts(sampleProducts)
//    }

    private suspend fun addSampleData() {
        val sampleProducts = listOf(
            Product(name = "Sản phẩm A", price = 100.0, quantity = 1, priceInNumber = 100),
            Product(name = "Sản phẩm B", price = 200.0, quantity = 2, priceInNumber = 200),
            Product(name = "Sản phẩm C", price = 300.0, quantity = 3, priceInNumber = 300)
        )

        Log.d("DEBUG", "Thêm dữ liệu mẫu: $sampleProducts")
        productDao.insertProducts(sampleProducts) // Gọi phương thức chèn dữ liệu
    }


    private fun updateTotalPrice() {
        val totalPrice = calculateTotalPrice()
        tvTotalPrice.text = "$totalPrice vnd"
    }

    private fun calculateTotalPrice(): Int {
        return productList.sumOf { it.quantity * it.priceInNumber }
    }
}