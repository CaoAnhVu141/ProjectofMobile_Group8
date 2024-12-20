package com.example.projectofmobile_group8

import ProductRecyclerViewAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaymentActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnOrder: Button
    private lateinit var tvTotalPrice: TextView
    private lateinit var tvSubTotal: TextView
    private lateinit var productRecyclerViewAdapter: ProductRecyclerViewAdapter
    private lateinit var productList: ArrayList<Product>
    private lateinit var btnBackToCart: ImageView
    private lateinit var llInforUser: LinearLayout
    private lateinit var llPaymentMethod: LinearLayout
    private lateinit var tvPaymentMethod: TextView
    private lateinit var updateAddressLauncher: ActivityResultLauncher<Intent>
    private lateinit var selectPaymentLauncher: ActivityResultLauncher<Intent>
    private var totalPrice: Int = 0
    private var subtotalPrice: Int = 0

    // Biến lưu trữ thông tin người dùng
    private var currentName: String = "Nguyễn Văn A"
    private var currentPhone: String = "0123456789"
    private var currentProvince: String = "Thành phố Hồ Chí Minh"
    private var currentDistrict: String = "Thủ Đức"
    private var currentWard: String = "Thảo Điền"
    private var currentAddressType: String = "Nhà riêng"

    // Biến lưu trữ phương thức thanh toán
    private var currentPaymentMethod: String = "Tiền mặt"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buy_items)
        setControl()
        setEvent()
    }

    private fun setControl() {
        recyclerView = findViewById(R.id.rv_products_payment)
        btnOrder = findViewById(R.id.btn_order)
        tvTotalPrice = findViewById(R.id.tv_total_price)
        tvSubTotal = findViewById(R.id.tv_subtotal)
        llInforUser = findViewById(R.id.llInforUser)
        btnBackToCart = findViewById(R.id.ivBacktoCart)
        llPaymentMethod = findViewById(R.id.llpaymentmethod)
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod)

        // Nhận dữ liệu từ Intent @Đổ  dữ liệu vào
        productList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra("productList", Product::class.java) ?: arrayListOf()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra("productList") ?: arrayListOf()
        }
        subtotalPrice = intent.getIntExtra("totalPrice", 0)

        // Thiết lập RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerViewAdapter = ProductRecyclerViewAdapter(
            context = this,
            productList = productList,
            isForPayment = true
        )
        recyclerView.adapter = productRecyclerViewAdapter

        // Hiển thị tổng tiền
        tvSubTotal.text = "$subtotalPrice vnd"
        tvTotalPrice.text = "$subtotalPrice vnd"
    }

    private fun setEvent() {
        // Xử lý sự kiện nhấn vào InforUser
        llInforUser.setOnClickListener {
            val intent = Intent(this, UpdateAddressActivity::class.java)
            intent.putExtra("name", currentName)
            intent.putExtra("phone", currentPhone)
            intent.putExtra("province", currentProvince)
            intent.putExtra("district", currentDistrict)
            intent.putExtra("ward", currentWard)
            intent.putExtra("addressType", currentAddressType)
            updateAddressLauncher.launch(intent)
        }

        // Xử lý sự kiện nhấn vào llPaymentMethod
        llPaymentMethod.setOnClickListener {
            val intent = Intent(this, PaymentMethodActivity::class.java)
            intent.putExtra("paymentMethod", currentPaymentMethod)
            selectPaymentLauncher.launch(intent)
        }

        // Cập nhật thông tin địa chỉ khách hàng
        updateAddressLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    currentName = data.getStringExtra("name") ?: currentName
                    currentPhone = data.getStringExtra("phone") ?: currentPhone
                    currentProvince = data.getStringExtra("province") ?: currentProvince
                    currentDistrict = data.getStringExtra("district") ?: currentDistrict
                    currentWard = data.getStringExtra("ward") ?: currentWard
                    currentAddressType = data.getStringExtra("addressType") ?: currentAddressType

                    // Cập nhật giao diện
                    val addressText = "$currentWard, $currentDistrict, $currentProvince"
                    findViewById<TextView>(R.id.tvUserName).text = "Họ và Tên: $currentName"
                    findViewById<TextView>(R.id.tvUserAddress).text = "Địa chỉ: $addressText"
                }
            }
        }

        // Cập nhật phương thức thanh toán
        selectPaymentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    currentPaymentMethod = data.getStringExtra("paymentMethod") ?: currentPaymentMethod
                    tvPaymentMethod.text = currentPaymentMethod
                }
            }
        }

        // Quay về trang giỏ hàng
        btnBackToCart.setOnClickListener {
            finish()
        }

        // Xử lý sự kiện nút đặt hàng
        btnOrder.setOnClickListener {
            val intent = Intent(this, PaymentSuccess::class.java)
            startActivity(intent)
        }
    }
}
