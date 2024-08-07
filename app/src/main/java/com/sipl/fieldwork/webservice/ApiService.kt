package com.sipl.fieldwork.webservice

import com.sipl.fieldwork.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun loginUser(@Query("name") name : String):Response<LoginResponse>

    @POST("logout")
    suspend fun logoutUser():Response<LoginResponse>

    @POST("list-masters-updated")
    fun fetchMastersDataTobeUpdated(): Response<LoginResponse>
}