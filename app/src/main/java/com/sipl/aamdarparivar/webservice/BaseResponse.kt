package com.sipl.aamdarparivar.webservice

sealed class BaseResponse<out T> {
    data class Success<out T>(val data:T):BaseResponse<T>()
    data class Error(val exception: Throwable) : BaseResponse<Nothing>()
   // data class ResponseFailure<out T>(val data:T):BaseResponse<T>()
    data object Loading:BaseResponse<Nothing>()
}