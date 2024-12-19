package com.example.projectofmobile_group8

data class HomeProduct(
    val name: String,
    val price: Double,
    val description: String,
    val imageResource: Int,
    val smallImages: List<Int>,
    val sales: Int,            // Số lượng bán
    val addedDate: Long        // Thời gian thêm sản phẩm (timestamp)
)