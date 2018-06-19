package com.hampu.jetpackdemo.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long = 0
    @ColumnInfo(name = "first_name")
    var firstName: String? = null
    var address: String? = null

    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true
        val user = other as User?
        return user!!.userId == this.userId && user.firstName === this.firstName
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}