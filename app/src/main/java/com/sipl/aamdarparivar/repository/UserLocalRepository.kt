package com.sipl.aamdarparivar.repository

import androidx.lifecycle.LiveData
import com.sipl.aamdarparivar.database.dao.UsersDao
import com.sipl.aamdarparivar.database.entity.Users

class UserLocalRepository(private val userDao: UsersDao) {

    fun getUser(id: Int): LiveData<Users> {
        return userDao.getUsersById(id)
    }

    suspend fun insertUser(user: Users): Long {
        return userDao.insertUsers(user)
    }
    suspend fun getAllUsers(): LiveData<List<Users>> {
        return userDao.getAllUserss();
    }
    suspend fun updateUsers(user: Users) {
        userDao.updateUsers(user)
    }
    suspend fun deleteUser(user: Users) {
        userDao.deleteUser(user)
    }
    /*suspend fun getAllUsersWithAreaNames(): LiveData<List<UsersWithAreaNames>> {
        return userDao.getUserssWithAreaNames()
    }
    suspend fun getAllUsersWithAreaNamesById(userId: Int): UsersWithAreaNames? {
        return userDao.getUserssWithAreaNamesById(userId)
    }*/
    suspend fun getUserCount(): Int {
        return userDao.getUsersCount()
    }
    suspend fun deleteUserById(userId: Int):Int {
        return userDao.deleteUsersById(userId)
    }
}
