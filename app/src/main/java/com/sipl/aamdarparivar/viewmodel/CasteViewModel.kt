package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sipl.aamdarparivar.database.entity.Caste
import com.sipl.aamdarparivar.repository.CasteRepository

class CasteViewModel(private val casteRepository: CasteRepository) :ViewModel() {
    suspend fun getAllCastes(): LiveData<List<Caste>> {
        return casteRepository.getAllCastes()
    }
    suspend fun insertAll(items: List<Caste>) {
        casteRepository.insertCaste(items)

    }
    suspend fun getCasteById(id:String): LiveData<Caste> {
        return casteRepository.getCasteById(id)
    }
    suspend fun insertInitialRecords(items: List<Caste>) {
        if(casteRepository.getAllCastes().value.isNullOrEmpty())
        {
            casteRepository.insertCaste(items)
        }

    }
}