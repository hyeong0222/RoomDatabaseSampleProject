package com.example.roomdatabasesampleproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasesampleproject.model.User

class UserAdapter() : ListAdapter<User, UserViewHolder>(diffUtil) {

    public override fun getItem(position: Int): User = super.getItem(position)

    fun setUserItems(list: List<User>) = submit(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return DataBindingUtil.inflate<ItemTaskBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        ).let { TaskViewHolder(it, listener) }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        (holder as? TaskViewHolder)?.bind(getItem(position))
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

class UserViewHolder(
    private val binding: ItemTaskBinding,
    listener: TaskAdapter.OnTaskItemClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener { listener?.onTaskItemClick(bindingAdapterPosition) }

        itemView.setOnLongClickListener {
            listener?.onTaskItemLongClick(bindingAdapterPosition)
            return@setOnLongClickListener true
        }

        binding.checkbox.setOnClickListener {
            listener?.onTaskCompleteClick(bindingAdapterPosition)
        }
    }

    fun bind(task: Task) {
        binding.task = task
        binding.executePendingBindings()
    }
}