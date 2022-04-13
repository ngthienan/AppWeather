package com.annt.appweather

import com.annt.igitproject.db.AppDatabase
import android.app.Application

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
        private set
    }
    val database by lazy { AppDatabase.getInstance(instance) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}