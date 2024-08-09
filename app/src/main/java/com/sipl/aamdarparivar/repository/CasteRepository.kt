package com.sipl.aamdarparivar.repository

import androidx.lifecycle.LiveData
import com.sipl.aamdarparivar.database.dao.CasteDao
import com.sipl.aamdarparivar.database.entity.Caste

class CasteRepository(private val casteDao: CasteDao) {

    suspend fun insertCaste(caste: List<Caste>) {
        casteDao.insertAll(caste)
    }
    suspend fun getAllCastes(): LiveData<List<Caste>> {
        return casteDao.getAllCastes()
    }
    fun getCasteById(id: String): LiveData<Caste> {
        return casteDao.getCasteById(id)
    }
    suspend fun insertInitialRecords(items: List<Caste>) {
        if(casteDao.getAllCastes().value.isNullOrEmpty())
        {
            casteDao.insertAll(items)
        }

    }

}