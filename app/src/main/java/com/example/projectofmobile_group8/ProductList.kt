package com.example.projectofmobile_group8

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class ProductList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var productList: MutableList<HomeProduct>
    private lateinit var filteredProductList: MutableList<HomeProduct>
    private lateinit var searchEditText: EditText
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setControl()
        setEvent()
    }

    // Khởi tạo các thành phần giao diện và dữ liệu
    private fun setControl() {
        // Ánh xạ các view
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        tabLayout = findViewById(R.id.tabLayout)

        // Thiết lập Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Thiết lập RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        productList = createProductList()
        filteredProductList = productList.toMutableList()
        productAdapter = HomeProductAdapter(filteredProductList)
        recyclerView.adapter = productAdapter
    }

    // Thiết lập các sự kiện
    private fun setEvent() {
        // Sự kiện cho TabLayout
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                handleTabSelection(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Sự kiện tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterProducts(s.toString().trim())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Xử lý chọn Tab
    private fun handleTabSelection(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> showAllProducts()
            1 -> showBestSellingProducts()
            2 -> showNewestProducts()
            3 -> enableSearch()
        }
        productAdapter.notifyDataSetChanged()
    }

    // Hiển thị tất cả sản phẩm
    private fun showAllProducts() {
        searchEditText.visibility = View.GONE
        searchEditText.setText("")
        filteredProductList.clear()
        filteredProductList.addAll(productList)
    }

    // Hiển thị sản phẩm bán chạy
    private fun showBestSellingProducts() {
        searchEditText.visibility = View.GONE
        searchEditText.setText("")
        filteredProductList.clear()
        filteredProductList.addAll(getBestSellingProducts())
    }

    // Hiển thị sản phẩm mới nhất
    private fun showNewestProducts() {
        searchEditText.visibility = View.GONE
        searchEditText.setText("")
        filteredProductList.clear()
        filteredProductList.addAll(getNewestProducts())
    }

    // Hiển thị ô tìm kiếm
    private fun enableSearch() {
        searchEditText.visibility = View.VISIBLE
        searchEditText.requestFocus()
    }

    // Lọc sản phẩm theo từ khóa
    private fun filterProducts(query: String) {
        filteredProductList.clear()
        if (query.isEmpty()) {
            filteredProductList.addAll(productList)
        } else {
            filteredProductList.addAll(productList.filter { it.name.contains(query, ignoreCase = true) })
        }
        productAdapter.notifyDataSetChanged()
    }

    // Lấy danh sách sản phẩm bán chạy
    private fun getBestSellingProducts(): List<HomeProduct> {
        return productList.sortedByDescending { it.sales }
    }

    // Lấy danh sách sản phẩm mới nhất
    private fun getNewestProducts(): List<HomeProduct> {
        return productList.sortedByDescending { it.addedDate }
    }

    // Tạo danh sách sản phẩm mẫu
    private fun createProductList(): MutableList<HomeProduct> {
        return mutableListOf(
            HomeProduct("Nike1", 19.99, "Cotton T-shirt", R.drawable.giay, listOf(R.drawable.giay, R.drawable.giay2, R.drawable.giay3), sales = 50, addedDate = 1700000000000),
            HomeProduct("Nike2", 49.99, "Blue denim jeans", R.drawable.giay2, listOf(R.drawable.giay2, R.drawable.giay, R.drawable.giay3), sales = 120, addedDate = 1700100000000),
            HomeProduct("Nike3", 59.99, "Winter Jacket", R.drawable.giay3, listOf(R.drawable.giay3, R.drawable.giay, R.drawable.giay2), sales = 70, addedDate = 1699900000000),
            HomeProduct("Nike4", 79.99, "Running Shoes", R.drawable.giay, listOf(R.drawable.giay, R.drawable.giay3, R.drawable.giay2), sales = 30, addedDate = 1699800000000)
        )
    }

    // Xử lý quay lại màn hình trước
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
