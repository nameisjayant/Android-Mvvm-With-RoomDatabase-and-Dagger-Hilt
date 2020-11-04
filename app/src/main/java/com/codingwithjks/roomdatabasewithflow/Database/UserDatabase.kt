package com.codingwithjks.roomdatabasewithflow.Database

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwithjks.roomdatabasewithflow.Dao.UserDao
import com.codingwithjks.roomdatabasewithflow.Model.User
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao():UserDao

    }
