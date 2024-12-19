package com.example.productwithdb

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ProductDetailActivity : AppCompatActivity() {

    private var quantity = 1  // Biến lưu trữ số lượng
    private var currentImageIndex = 0  // Biến lưu trữ ảnh hiện tại
    private lateinit var smallImages: ArrayList<Int>  // Mảng các ảnh nhỏ

    private val handler = Handler(Looper.getMainLooper())  // Handler để xử lý các tác vụ sau mỗi 2 giây
    private val imageSwitcherRunnable = object : Runnable {
        override fun run() {
            switchMainImage()
            handler.postDelayed(this, 2000)  // Lặp lại mỗi 2 giây
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setupToolbar()  // Thiết lập Toolbar
        initializeData()  // Lấy dữ liệu từ Intent
        setupViews()  // Thiết lập các View
        setupImageSwitching()  // Bắt đầu chuyển đổi ảnh tự động
        setupQuantityButtons()  // Xử lý tăng/giảm số lượng
        setupAddToCartButton()  // Xử lý thêm vào giỏ hàng
    }

    // Thiết lập Toolbar
    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Hiển thị nút quay lại (Back)
    }

    // Lấy dữ liệu từ Intent
    private fun initializeData() {
        val productName = intent.getStringExtra("productName") ?: "Unknown"
        val productPrice = intent.getDoubleExtra("productPrice", -1.0)
        val productDescription = intent.getStringExtra("productDescription") ?: "No description available."
        val productImage = intent.getIntExtra("productImage", -1)
        smallImages = intent.getIntegerArrayListExtra("smallImages") ?: arrayListOf()

        // Gán dữ liệu vào các View
        findViewById<TextView>(R.id.productName).text = productName
        findViewById<TextView>(R.id.productPrice).text = "$${productPrice}"
        findViewById<TextView>(R.id.productDescription).text = productDescription

        if (productImage != -1) {
            findViewById<ImageView>(R.id.productImage).setImageResource(productImage)
        }
    }

    // Thiết lập các View và Spinner
    private fun setupViews() {
        setupSizeSpinner()
        setupSmallImageClickListeners()
    }

    // Thiết lập Spinner cho kích cỡ
    private fun setupSizeSpinner() {
        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)
        val sizes = arrayOf("37", "38", "39", "40", "41", "42", "43")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sizeSpinner.adapter = adapter
    }

    // Thiết lập lắng nghe sự kiện nhấp vào ảnh nhỏ
    private fun setupSmallImageClickListeners() {
        val smallImageViews = listOf(
            findViewById<ImageView>(R.id.smallImage1),
            findViewById<ImageView>(R.id.smallImage2),
            findViewById<ImageView>(R.id.smallImage3)
        )

        smallImageViews.forEachIndexed { index, imageView ->
            imageView.setImageResource(smallImages.getOrNull(index) ?: R.drawable.default_image)
            imageView.setOnClickListener {
                updateMainImage(index)
            }
        }
    }

    /// Cập nhật ảnh chính và độ sáng của ảnh nhỏ

    private fun updateMainImage(index: Int) {
        val imageView: ImageView = findViewById(R.id.productImage)
        imageView.setImageResource(smallImages.getOrNull(index) ?: R.drawable.default_image)
        setAlphaForImages(index)
    }

    // Chuyển đổi ảnh chính tự động
    private fun setupImageSwitching() {
        handler.postDelayed(imageSwitcherRunnable, 2000)
    }

    private fun switchMainImage() {
        updateMainImage(currentImageIndex)
        currentImageIndex = (currentImageIndex + 1) % smallImages.size
    }

    // Thay đổi độ sáng của các ảnh nhỏ
    private fun setAlphaForImages(selectedIndex: Int) {
        val smallImageViews = listOf(
            findViewById<ImageView>(R.id.smallImage1),
            findViewById<ImageView>(R.id.smallImage2),
            findViewById<ImageView>(R.id.smallImage3)
        )

        smallImageViews.forEachIndexed { index, imageView ->
            imageView.alpha = if (index == selectedIndex) 1f else 0.5f
        }
    }

    // Xử lý tăng/giảm số lượng sản phẩm
    private fun setupQuantityButtons() {
        val quantityEditText: EditText = findViewById(R.id.quantityEditText)
        val decreaseButton: Button = findViewById(R.id.decreaseButton)
        val increaseButton: Button = findViewById(R.id.increaseButton)

        quantityEditText.setText(quantity.toString())

        decreaseButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityEditText.setText(quantity.toString())
            }
        }

        increaseButton.setOnClickListener {
            quantity++
            quantityEditText.setText(quantity.toString())
        }
    }

    // Xử lý thêm vào giỏ hàng
    private fun setupAddToCartButton() {
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        addToCartButton.setOnClickListener {
            addToCart()
        }
    }

    private fun addToCart() {
        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)
        val selectedSize = sizeSpinner.selectedItem.toString()

        if (selectedSize.isNotEmpty()) {
            val productName = findViewById<TextView>(R.id.productName).text
            val cartMessage = "Bạn đã thêm $quantity đôi giày $productName có size $selectedSize vào giỏ hàng thành công"
            Toast.makeText(this, cartMessage, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Vui lòng chọn kích cỡ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()  // Quay lại màn hình trước
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(imageSwitcherRunnable)  // Hủy bỏ tác vụ khi Activity bị hủy
    }
}
