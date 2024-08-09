package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.aamdarparivar.repository.GenderRepository

class GenderViewModelFactory(private val areRepository: GenderRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GenderViewModel::class.java)) {
            return GenderViewModel(areRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}