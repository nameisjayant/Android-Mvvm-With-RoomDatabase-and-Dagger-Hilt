package com.codingwithjks.roomdatabasewithflow.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codingwithjks.roomdatabasewithflow.Model.User
import com.codingwithjks.roomdatabasewithflow.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel
@ViewModelInject
constructor(private val userRepository: UserRepository) : ViewModel(){
    val getUser:LiveData<List<User>> get() =
        userRepository.getUser.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)

    fun insert(user:User){
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }
}