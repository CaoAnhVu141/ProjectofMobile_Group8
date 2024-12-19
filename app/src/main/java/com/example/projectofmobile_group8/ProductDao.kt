package com.example.projectofmobile_group8

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProducts(products: List<Product>) // Thêm danh sách sản phẩm

    @Update
    suspend fun updateProduct(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int // Đếm số lượng sản phẩm trong bảng

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}
