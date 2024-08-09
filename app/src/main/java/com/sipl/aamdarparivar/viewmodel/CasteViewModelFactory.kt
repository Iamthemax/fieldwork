package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sipl.aamdarparivar.repository.CasteRepository

class CasteViewModelFactory(private val repository: CasteRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CasteViewModel::class.java)) {
            return CasteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}