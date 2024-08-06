package com.sipl.fieldwork.utils

import android.content.Context
import android.util.Log
import com.sipl.fieldwork.utils.XConst.MYTAG

class MySharedPref(mContext:Context) {


    fun getToken():String{
        return "tokennnnnnnnnnnnn";
    }
    fun clearAllPrefs()
    {
        Log.d(MYTAG,"Cleared All Shared Prefs")
    }
}