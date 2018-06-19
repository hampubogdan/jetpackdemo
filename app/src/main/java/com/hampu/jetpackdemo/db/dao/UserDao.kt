package com.hampu.jetpackdemo.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.hampu.jetpackdemo.db.model.User

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(vararg user: User)

    @Delete
    fun deleteUser(vararg user: User)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("SELECT * FROM User")
    fun usersByFirstName(): DataSource.Factory<Int, User>

}