package com.sipl.fieldwork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.fieldwork.repository.ConnectivityRepository
import com.sipl.fieldwork.repository.GenderRepository
import com.sipl.fieldwork.repository.MastersRepository

class SplashScreenViewModelFactory(private val mastersRepository: MastersRepository,val connectivityRepository: ConnectivityRepository):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(mastersRepository,connectivityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}