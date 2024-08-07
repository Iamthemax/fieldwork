package com.sipl.fieldwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sipl.fieldwork.database.entity.Caste
import com.sipl.fieldwork.repository.CasteRepository

class CasteViewModel(private val casteRepository: CasteRepository) :ViewModel() {
    suspend fun getAllCastes(): LiveData<List<Caste>> {
        return casteRepository.getAllCastes()
    }
    suspend fun insertAll(items: List<Caste>) {
        casteRepository.insertCaste(items)

    }
    suspend fun getCasteById(id:String): Caste {
        return casteRepository.getCasteById(id)
    }
    suspend fun insertInitialRecords(items: List<Caste>) {
        if(casteRepository.getAllCastes().value.isNullOrEmpty())
        {
            casteRepository.insertCaste(items)
        }

    }
}