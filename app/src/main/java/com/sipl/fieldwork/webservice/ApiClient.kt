package com.sipl.fieldwork.webservice

import android.util.Log
import com.sipl.fieldwork.BuildConfig
import com.sipl.fieldwork.application.MyApp
import com.sipl.fieldwork.application.MyApp.Companion.getAppContext
import com.sipl.fieldwork.utils.MySharedPref
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL= BuildConfig.API_URL;
    private val READ_TIMEOUT =BuildConfig.READ_TIMEOUT;
    private  val CONNECT_TIMEOUT =BuildConfig.CONNECT_TIMEOUT;
    private val logInterceptor by lazy {
       HttpLoggingInterceptor().apply {
           level=if(BuildConfig.DEBUG)
           {
               HttpLoggingInterceptor.Level.BODY
           }else{
               HttpLoggingInterceptor.Level.NONE
           }
       }
   }
    private val getOkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor{
                chain->
                val request=chain.request().newBuilder()
                    .addHeader("Authorization", getAuthToken())
                    .build()
                chain.proceed(request)
            }.addInterceptor(logInterceptor)
            .addInterceptor(AuthInterceptor(getAppContext()))
            .connectTimeout(CONNECT_TIMEOUT.toLong(),TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(),TimeUnit.SECONDS)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun  create():ApiService{
        return retrofit.create(ApiService::class.java)
    }
    private fun getAuthToken():String{
        val pref=MySharedPref(getAppContext())
        Log.d("mytag", pref.getToken())
        return pref.getToken()
    }
}