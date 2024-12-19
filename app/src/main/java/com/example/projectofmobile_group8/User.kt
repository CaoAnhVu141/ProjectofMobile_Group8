package com.example.projectofmobile_group8
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    var password: String,
) : Parcelable