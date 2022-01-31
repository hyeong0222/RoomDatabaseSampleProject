package com.example.roomdatabasesampleproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "firstName")
    var firstName: String? = "",

    @ColumnInfo(name = "lastName")
    var lastName: String? = "",

    @ColumnInfo(name = "age")
    var age: Int,
)
