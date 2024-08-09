package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sipl.aamdarparivar.model.login.LoginResponse
import com.sipl.aamdarparivar.repository.UserRepository
import com.sipl.aamdarparivar.webservice.BaseResponse
import kotlinx.coroutines.launch

class LoginViewModel(private  val userRepository: UserRepository) : ViewModel() {

    private val _loginData=MutableLiveData<BaseResponse<LoginResponse>>()
    val loginData: LiveData<BaseResponse<LoginResponse>> get() = _loginData

    fun loginUser(mobile :String,password:String)
    {
        viewModelScope.launch {
            _loginData.postValue(BaseResponse.Loading)
            try {
                userRepository.loginUser(mobile,password);
                userRepository.getLogin.observeForever { response ->
                    _loginData.postValue(response)
                }
            }catch (e:Exception){
                _loginData.postValue(BaseResponse.Error(e))
            }
        }
    }
}