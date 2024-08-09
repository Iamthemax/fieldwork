package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.aamdarparivar.repository.UserLocalRepository

class UserLocalViewModelFactory(private val repository: UserLocalRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserLocalViewModel::class.java)) {
            return UserLocalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}