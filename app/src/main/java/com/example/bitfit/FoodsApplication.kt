package com.example.bitfit

import android.app.Application

class FoodsApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}