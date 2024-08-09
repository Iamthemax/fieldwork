package com.sipl.aamdarparivar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.sipl.aamdarparivar.database.entity.AreaItem


@Dao
interface AreaDao {

    @Query("SELECT * FROM area where is_active=1 ORDER BY name ASC ")
      fun getAllArea(): LiveData<List<AreaItem>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    suspend  fun insertAll(items: List<AreaItem>)

    @Query("SELECT * FROM area WHERE location_type=2 AND is_active=1  ORDER BY name ASC")
     fun getAllDistrict(): LiveData<List<AreaItem>>

    @Query("SELECT * FROM area WHERE location_type=3  AND  parent_id = :location_id   AND is_active =1  ORDER BY name ASC")
     fun getAllTalukas(location_id: String): LiveData<List<AreaItem>>

    @Query("SELECT * FROM area WHERE location_type=4  AND  parent_id = :location_id  AND is_active =1 ORDER BY name ASC")
     fun getVillageByTaluka(location_id: String): LiveData<List<AreaItem>>

    @Query("SELECT * FROM area WHERE location_id = :location_id AND is_active=1 ORDER BY name ASC")
     fun getAreaByLocationId(location_id: String): LiveData<AreaItem>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AreaItem):Long

    @Update
    suspend fun update(entity: AreaItem):Int

    @Transaction
    suspend fun insertInitialRecords(items: List<AreaItem>) {
            insertAll(items)
    }

    @Query("SELECT count(id) FROM area")
    fun getAllAreaCount(): LiveData<Int>
}