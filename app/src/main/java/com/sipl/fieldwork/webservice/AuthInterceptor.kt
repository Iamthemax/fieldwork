package com.sipl.fieldwork.webservice

import android.content.Context
import android.content.Intent
import com.sipl.fieldwork.ui.startup.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val mContext : Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            val intent = Intent(mContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            mContext.startActivity(intent)
        }
        return response
    }
}