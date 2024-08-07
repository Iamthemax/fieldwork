package com.sipl.fieldwork.repository

import androidx.lifecycle.LiveData
import com.sipl.fieldwork.database.dao.GenderDao
import com.sipl.fieldwork.database.entity.Caste
import com.sipl.fieldwork.database.entity.Gender
import com.sipl.fieldwork.webservice.ApiService

class GenderRepository(private val genderDao: GenderDao) {

    suspend fun getGenders(): LiveData<List<Gender>> {
        return genderDao.getAllGenders()
    }
    suspend fun getGenderById(id: String): LiveData<Gender> {
        return genderDao.getGenderById(id)
    }
    suspend fun insertGenders(genders: List<Gender>) {
        return genderDao.insertAll(genders)
    }
}