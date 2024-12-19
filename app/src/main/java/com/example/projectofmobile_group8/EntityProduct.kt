package com.example.projectofmobile_group8

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tên bảng trong SQLite sẽ là "cart_items"
@Entity(tableName = "cart_items")
data class EntityProduct(
    // PrimaryKey để xác định mỗi sản phẩm duy nhất, autoGenerate = true để tự động tăng giá trị id
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Tên sản phẩm
    val name: String,

    // Giá sản phẩm dưới dạng chuỗi (dễ dàng lưu trữ tiền tệ có dấu phân cách)
    val price: String,

    // Số lượng sản phẩm trong giỏ hàng
    var quantity: Int = 1,

    // Giá sản phẩm dưới dạng số (dùng để tính toán tổng tiền trong giỏ hàng)
    val priceInNumber: Int
)
