package com.sipl.fieldwork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.fieldwork.repository.GenderRepository

class SplashScreenViewModelFactory(private val mastersRepository: GenderRepository):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return SplashScreenViewModel(mastersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}