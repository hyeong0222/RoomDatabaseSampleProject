package com.example.roomdatabasesampleproject.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdatabasesampleproject.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert
    suspend fun addUser(user: User)
}