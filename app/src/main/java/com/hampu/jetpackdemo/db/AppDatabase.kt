package com.hampu.jetpackdemo.db

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.hampu.jetpackdemo.db.dao.UserDao
import com.hampu.jetpackdemo.db.model.User

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
@Database(entities = [(User::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "UserDb"
    }
}