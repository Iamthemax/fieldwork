package com.example.fieldwork.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fieldwork.model.login.LoginResponse
import com.example.fieldwork.webservice.ApiService
import com.example.fieldwork.webservice.BaseResponse
import com.example.fieldwork.webservice.ErrorResponse
import com.google.gson.Gson

class UserRepository(private val apiService: ApiService) {

    private val loginData = MutableLiveData<BaseResponse<LoginResponse>>()
    val getLogin: LiveData<BaseResponse<LoginResponse>>
        get() = loginData

    suspend fun loginUser(name: String) {
        try {
            val result = apiService.loginUser(name)
            if (result.isSuccessful) {
                loginData.postValue(BaseResponse.Success(result.body()!!))
            } else {
                val errorBody = result.errorBody()?.string()

                val errorResponse = errorBody?.let {
                    try {
                        // Parse the error body to ErrorResponse class
                        Gson().fromJson(it, ErrorResponse::class.java)
                    } catch (e: Exception) {
                        // Handle JSON parsing error
                        ErrorResponse("Error parsing error body", result.code())
                    }
                }
                loginData.postValue(BaseResponse.Error(Throwable(errorResponse?.message ?: "Unknown error")))
            }
        } catch (e: Exception) {
            loginData.postValue(BaseResponse.Error(e))
        }
    }
}
