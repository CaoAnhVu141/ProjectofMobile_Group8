package com.example.projectofmobile_group8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: String,
    var quantity: Int = 1,
    val priceInNumber: Int
) : Parcelable
