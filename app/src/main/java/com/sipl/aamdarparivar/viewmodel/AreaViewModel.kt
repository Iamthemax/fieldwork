package com.sipl.aamdarparivar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sipl.aamdarparivar.database.entity.AreaItem
import com.sipl.aamdarparivar.repository.AreaRepository
import com.sipl.aamdarparivar.utils.XConst
import kotlinx.coroutines.launch

class AreaViewModel(private val areaRepository: AreaRepository) : ViewModel() {



    suspend fun getAllAreas(): LiveData<List<AreaItem>> {
        return  areaRepository.getAllArea();
    }
    suspend fun getAllTalukas(id:String): LiveData<List<AreaItem>> {
        return areaRepository.getAllTalukas(id);
    }
     fun getAreaByLocationId(id:String): LiveData<AreaItem> {
        return areaRepository.getAreaByLocationId(id);
    }
    fun insertAll(items: List<AreaItem>) {
        viewModelScope.launch {
            areaRepository.insertAll(items)
        }
    }
    fun insertInitialRecords(items: List<AreaItem>) {
        viewModelScope.launch {
            if(areaRepository.getAllArea().value.isNullOrEmpty())
            {
                areaRepository.insertInitialRecords(items)
            }else{
                Log.d(XConst.MYTAG,"Area is not empty")
            }
        }
    }
    suspend fun getAllArea(): LiveData<List<AreaItem>> {
        return areaRepository.getAllArea();
    }

}