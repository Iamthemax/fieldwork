package com.sipl.fieldwork.repository

import androidx.lifecycle.LiveData
import com.sipl.fieldwork.database.dao.CasteDao
import com.sipl.fieldwork.database.entity.Caste

class CasteRepository(private val casteDao: CasteDao) {

    suspend fun insertCaste(caste: List<Caste>) {
        casteDao.insertAll(caste)
    }
    suspend fun getAllCastes(): LiveData<List<Caste>> {
        return casteDao.getAllCastes()
    }
    suspend fun getCasteById(id: String): Caste {
        return casteDao.getCasteById(id)
    }
    suspend fun insertInitialRecords(items: List<Caste>) {
        if(casteDao.getAllCastes().value.isNullOrEmpty())
        {
            casteDao.insertAll(items)
        }

    }

}