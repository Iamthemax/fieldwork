package com.sipl.aamdarparivar.repository

import androidx.lifecycle.LiveData
import com.sipl.aamdarparivar.database.dao.AreaDao
import com.sipl.aamdarparivar.database.entity.AreaItem

class AreaRepository(private val areaDao: AreaDao) {
    suspend fun insertAll(items: List<AreaItem>) {
        areaDao.insertAll(items)
    }
    suspend fun getAllTalukas(id:String): LiveData<List<AreaItem>> {
        return areaDao.getAllTalukas(id);
    }
    suspend fun getVillageByTaluka(id:String): LiveData<List<AreaItem>>{
        return areaDao.getVillageByTaluka(id);
    }
     fun getAreaByLocationId(id:String): LiveData<AreaItem> {
        return areaDao.getAreaByLocationId(id);
    }
    suspend fun insertInitialRecords(items: List<AreaItem>) {
        areaDao.insertInitialRecords(items)
    }
    suspend fun insetData(entity: AreaItem) {
        areaDao.insert(entity)
    }
    suspend fun updateData(entity: AreaItem) {
        areaDao.update(entity)
    }
    suspend fun getAllArea(): LiveData<List<AreaItem>> {
        return areaDao.getAllArea();
    }

}