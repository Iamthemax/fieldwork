package com.sipl.aamdarparivar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sipl.aamdarparivar.model.login.LoginResponse
import com.sipl.aamdarparivar.webservice.ApiService
import com.sipl.aamdarparivar.webservice.BaseResponse
import com.sipl.aamdarparivar.webservice.ErrorResponse

class MastersRepository(private val apiService: ApiService)
{
    private val mastersData = MutableLiveData<BaseResponse<LoginResponse>>()
    val getLogin: LiveData<BaseResponse<LoginResponse>>
        get() = mastersData
    suspend fun getMastersFromApi() {
        try {
            val result = apiService.getMasters()
            if (result.isSuccessful) {
                mastersData.postValue(BaseResponse.Success(result.body()!!))
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
                mastersData.postValue(BaseResponse.Error(Throwable(errorResponse?.message ?: "Unknown error")))
            }
        } catch (e: Exception) {
            mastersData.postValue(BaseResponse.Error(e))
        }
    }
}