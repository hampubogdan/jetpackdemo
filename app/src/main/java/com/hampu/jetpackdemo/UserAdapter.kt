package com.hampu.jetpackdemo

import com.hampu.jetpackdemo.db.model.User
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.arch.paging.PagedListAdapter
import android.view.View


/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
class UserAdapter(private val clickListener: (Int) -> Unit) : PagedListAdapter<User, UserAdapter.UserItemViewHolder>(User.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user_list, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bindTo(user)
            holder.itemView.setOnClickListener { clickListener(position) }
        }
    }

    inner class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.userName)
        var userId: TextView = itemView.findViewById(R.id.userId)

        fun bindTo(user: User) {
            userName.text = user.firstName
            userId.text = user.userId.toString()
        }
    }
}