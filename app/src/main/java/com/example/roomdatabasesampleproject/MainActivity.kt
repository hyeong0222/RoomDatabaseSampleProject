package com.example.roomdatabasesampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasesampleproject.adapter.UserAdapter
import com.example.roomdatabasesampleproject.model.User
import com.example.roomdatabasesampleproject.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[UserViewModel::class.java]
        adapter = UserAdapter()

        addUserButton = findViewById(R.id.button_add)
        addUserButton.setOnClickListener {
            val dialog =
                AlertDialog.Builder(this).setTitle("Add User")
                    .setView(R.layout.dialog_add_user)
                    .setPositiveButton("Add") { _, _ ->
                        val firstName: TextInputEditText = findViewById(R.id.first_name_edit_text)
                        val lastName: TextInputEditText = findViewById(R.id.last_name_edit_text)
                        val age: TextInputEditText = findViewById(R.id.age_edit_text)
                        addUser(User(0, firstName.text.toString(), lastName.text.toString(), age.text as Int))
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
            dialog.show()
        }

        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        recyclerView = findViewById(R.id.rv_todo_list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.readAllData.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun addUser(user: User) {
        viewModel.addUser()
    }
}