package com.example.projectofmobile_group8

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Thêm ID là khóa chính
    val name: String,
    val price: Double,
    var quantity: Int = 1,
    val priceInNumber: Int
) : Parcelable {

    // Phương thức để tính tổng giá của sản phẩm
    fun getTotalPrice(): Double {
        return price * quantity
    }
}
