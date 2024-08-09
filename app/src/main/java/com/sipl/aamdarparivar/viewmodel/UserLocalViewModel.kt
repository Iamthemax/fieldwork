package com.sipl.aamdarparivar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sipl.aamdarparivar.database.entity.Users
import com.sipl.aamdarparivar.repository.UserLocalRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class UserLocalViewModel(private val userRepository: UserLocalRepository):ViewModel() {

    fun insertUser(user: Users) : Deferred<Long>{
       return viewModelScope.async {
          val id= userRepository.insertUser(user)
           return@async id
       }
    }
}