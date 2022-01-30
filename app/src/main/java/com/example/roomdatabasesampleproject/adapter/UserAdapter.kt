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

    fun setTaskItems(list: List<User>) = submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val person = getItem(position)
        holder.apply {
            firstName.text = person.firstName
            lastName.text = person.lastName
            age.text = person.age.toString()
        }
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

    val firstName: TextView = view.findViewById(R.id.tv_first_name)
    val lastName: TextView = view.findViewById(R.id.tv_last_name)
    val age: TextView = view.findViewById(R.id.tv_age)


}