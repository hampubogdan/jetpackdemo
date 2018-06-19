package com.hampu.jetpackdemo.db.viewmodel

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.hampu.jetpackdemo.db.dao.UserDao
import com.hampu.jetpackdemo.db.model.User
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
class UserViewModel : ViewModel() {

    lateinit var userList: LiveData<PagedList<User>>

    fun init(userDao: UserDao) {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20).build()

        userList = LivePagedListBuilder(userDao.usersByFirstName(), pagedListConfig)
                .build()

    }
}

class RxUserViewModel : ViewModel() {

    lateinit var userList: Flowable<PagedList<User>>

    fun init(userDao: UserDao) {
        userList = RxPagedListBuilder(
                userDao.usersByFirstName(),
                /* page size */ 50
        ).buildFlowable(BackpressureStrategy.LATEST)
    }
}