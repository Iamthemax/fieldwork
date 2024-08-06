package com.sipl.fieldwork.webservice

import com.sipl.fieldwork.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("login")
    suspend fun loginUser(@Query("name") name : String):Response<LoginResponse>
}