package com.sipl.fieldwork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sipl.fieldwork.database.entity.Users
import com.sipl.fieldwork.repository.UserLocalRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserLocalViewModel(private val userRepository: UserLocalRepository):ViewModel() {

    fun insertUser(user: Users) : Deferred<Long>{
       return viewModelScope.async {
          val id= userRepository.insertUser(user)
           return@async id
       }
    }
}