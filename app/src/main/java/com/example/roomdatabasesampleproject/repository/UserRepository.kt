package com.example.roomdatabasesampleproject.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasesampleproject.database.dao.UserDao
import com.example.roomdatabasesampleproject.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}