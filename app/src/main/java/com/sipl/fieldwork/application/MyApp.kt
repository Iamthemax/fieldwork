package com.sipl.fieldwork.application

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
    }

    companion object {
        private lateinit var instance: MyApp

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

}