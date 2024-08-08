package com.sipl.fieldwork.webservice

import com.sipl.fieldwork.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("dbb8354f-77e8-4201-b3a3-9bc7ae36ab16")
    suspend fun loginUser(
        @Query("mobile") mobile : String,
        @Query("password") password : String,
        ):Response<LoginResponse>

    @POST("logout")
    suspend fun logoutUser():Response<LoginResponse>

    @POST("list-masters-updated")
    fun getMasters(): Response<LoginResponse>
}