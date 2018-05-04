package com.m_d128.android_lifecycle

import android.app.Application
import android.util.Log

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.v("A", "Application onCreate")
    }

}