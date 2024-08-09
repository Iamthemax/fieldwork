package com.sipl.aamdarparivar.model.login

data class LoginResponse(
    val code: Int,
    val message: String,
    val resdata: Resdata,
    val result: Boolean
)