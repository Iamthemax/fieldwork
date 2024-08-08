package com.sipl.fieldwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sipl.fieldwork.model.login.LoginResponse
import com.sipl.fieldwork.repository.ConnectivityRepository
import com.sipl.fieldwork.repository.GenderRepository
import com.sipl.fieldwork.repository.MastersRepository
import com.sipl.fieldwork.webservice.BaseResponse
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val masterRepository: MastersRepository,private val connectivityRepository: ConnectivityRepository) : ViewModel() {
    
    private val _mastersData= MutableLiveData<BaseResponse<LoginResponse>>()
    val mastersData: LiveData<BaseResponse<LoginResponse>> get() = _mastersData
    val isOnline = connectivityRepository.isConnected.asLiveData()

    fun getMastersFromApi()
    {
        viewModelScope.launch {
            _mastersData.postValue(BaseResponse.Loading)
            try {
                masterRepository.getMastersFromApi()
                masterRepository.getLogin.observeForever { response ->
                    _mastersData.postValue(response)
                }
            }catch (e:Exception){
                _mastersData.postValue(BaseResponse.Error(e))
            }
        }
    }

}