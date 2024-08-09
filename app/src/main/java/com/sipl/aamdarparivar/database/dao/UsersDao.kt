package com.sipl.aamdarparivar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sipl.aamdarparivar.database.entity.Users

@Dao
interface UsersDao {
    @Insert
    suspend fun insertUsers(user: Users) :Long

    @Update
    suspend fun updateUsers(user: Users):Int

    @Delete
    suspend fun deleteUser(user: Users):Int

    @Query("SELECT * FROM users WHERE isSynced='0'")
    fun getAllUserss(): LiveData<List<Users>>

    @Query("SELECT * FROM users WHERE id = :id")
     fun getUsersById(id: Int): LiveData<Users>


    @Query("DELETE FROM users WHERE id = :id")
     suspend fun deleteUsersById(id: Int): Int



    @Query("SELECT COUNT(*) FROM users WHERE isSynced='0'")
    suspend fun getUsersCount(): Int

    /*@Query("SELECT l.*, village.name AS villageName, district.name AS districtName, taluka.name AS talukaName " +
            "FROM users l " +
            "LEFT JOIN area AS village ON l.village = village.location_id " +
            "LEFT JOIN area AS district ON l.district = district.location_id " +
            "LEFT JOIN area AS taluka ON l.taluka = taluka.location_id WHERE isSynced='0' ORDER BY id DESC")
     fun getUserssWithAreaNames(): LiveData<List<UsersWithAreaNames>>


    @Query("SELECT l.*, village.name AS villageName, district.name AS districtName, taluka.name AS talukaName " +
            "FROM users l " +
            "LEFT JOIN area AS village ON l.village = village.location_id " +
            "LEFT JOIN area AS district ON l.district = district.location_id " +
            "LEFT JOIN area AS taluka ON l.taluka = taluka.location_id " +
            "WHERE l.id = :userId AND isSynced='0'")
     fun getUserssWithAreaNamesById(userId: Int): LiveData<UsersWithAreaNames>?*/
}