package com.example.roomdatabasesampleproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasesampleproject.R
import com.example.roomdatabasesampleproject.model.User

class UserAdapter : ListAdapter<User, UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val firstName: TextView = view.findViewById(R.id.tv_first_name)
    private val lastName: TextView = view.findViewById(R.id.tv_last_name)
    private val age: TextView = view.findViewById(R.id.tv_age)

    fun bind(user: User) {
        firstName.text = user.firstName
        lastName.text = user.lastName
        age.text = user.age.toString()
    }
}