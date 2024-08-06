package com.example.fieldwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldwork.model.login.LoginResponse
import com.example.fieldwork.repository.UserRepository
import com.example.fieldwork.webservice.BaseResponse
import kotlinx.coroutines.launch

class LoginViewModel(private  val userRepository: UserRepository) : ViewModel() {

    private val _loginData=MutableLiveData<BaseResponse<LoginResponse>>()
    val loginData: LiveData<BaseResponse<LoginResponse>> get() = _loginData

    fun loginUser(name :String)
    {
        viewModelScope.launch {
            _loginData.postValue(BaseResponse.Loading)
            try {
                userRepository.loginUser(name);
                userRepository.getLogin.observeForever { response ->
                    _loginData.postValue(response)
                }
            }catch (e:Exception){
                _loginData.postValue(BaseResponse.Error(e))
            }
        }
    }
}