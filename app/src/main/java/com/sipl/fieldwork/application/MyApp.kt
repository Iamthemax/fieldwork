package com.sipl.fieldwork.application

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MyApp : Application() {

    private lateinit var application:Application
    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate()
        application=this
    }

     fun getMyApplicationContext():Context{
        return application.applicationContext;
    }

}