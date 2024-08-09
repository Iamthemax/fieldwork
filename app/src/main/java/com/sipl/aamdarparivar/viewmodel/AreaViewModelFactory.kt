package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.aamdarparivar.repository.AreaRepository

class AreaViewModelFactory(private val areRepository: AreaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AreaViewModel::class.java)) {
            return AreaViewModel(areRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
