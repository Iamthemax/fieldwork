package com.sipl.fieldwork.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sipl.fieldwork.model.login.LoginResponse
import com.sipl.fieldwork.webservice.ApiService
import com.sipl.fieldwork.webservice.BaseResponse
import com.sipl.fieldwork.webservice.ErrorResponse
import com.google.gson.Gson

class UserRepository(private val apiService: ApiService) {

    private val loginData = MutableLiveData<BaseResponse<LoginResponse>>()
    val getLogin: LiveData<BaseResponse<LoginResponse>>
        get() = loginData
    suspend fun loginUser(mobile: String,password:String) {
        try {
            val result = apiService.loginUser(mobile,password)
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
