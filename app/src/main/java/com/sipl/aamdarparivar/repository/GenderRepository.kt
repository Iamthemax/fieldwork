package com.sipl.aamdarparivar.repository

import androidx.lifecycle.LiveData
import com.sipl.aamdarparivar.database.dao.GenderDao
import com.sipl.aamdarparivar.database.entity.Gender

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