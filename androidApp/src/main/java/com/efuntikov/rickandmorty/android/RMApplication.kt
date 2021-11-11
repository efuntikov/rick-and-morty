package com.efuntikov.rickandmorty.android

import android.app.Application
import android.util.Log

class RMApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.i("+++", "Application is created")
    }
}