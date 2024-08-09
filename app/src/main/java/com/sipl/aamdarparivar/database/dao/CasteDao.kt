package com.sipl.aamdarparivar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sipl.aamdarparivar.database.entity.Caste

@Dao
interface CasteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAll(items: List<Caste>)

    @Query("DELETE FROM gender")
    suspend  fun deleteAllCastes():Int

    @Query("SELECT COUNT(*) FROM gender WHERE is_active=1")
    suspend  fun getRowCount(): Int
    @Transaction
    suspend fun insertInitialRecords(items: List<Caste>) {
        deleteAllCastes()
        insertAll(items)
    }

    @Query("SELECT * FROM gender WHERE is_active=1 ORDER BY id ASC")
     fun getAllCastes(): LiveData<List<Caste>>

    @Query("SELECT * FROM gender WHERE id = :id AND is_active=1")
     fun getCasteById(id: String): LiveData<Caste>

}