package com.example.projectofmobile_group8

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "DB_new"
        ).build()
    }
}
