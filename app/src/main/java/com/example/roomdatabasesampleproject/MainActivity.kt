package com.example.roomdatabasesampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var addUserButton: Button

    private val userViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[UserViewModel::class.java]
    }
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addUserButton = findViewById(R.id.button_add)
        addUserButton.setOnClickListener {
            val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null)
            val dialog =
                AlertDialog.Builder(this).setTitle("Add User")
                    .setView(view)
                    .setPositiveButton("Add") { _, _ ->
                        val firstName: TextInputEditText =
                            view.findViewById(R.id.first_name_edit_text)
                        val lastName: TextInputEditText =
                            view.findViewById(R.id.last_name_edit_text)
                        val age: TextInputEditText = view.findViewById(R.id.age_edit_text)
                        addUser(
                            User(
                                firstName = firstName.text.toString(),
                                lastName = lastName.text.toString(),
                                age = age.text.toString().toInt()
                            )
                        )
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
            adapter = userAdapter
        }
    }

    private fun observeViewModel() {
        userViewModel.readAllData.observe(this) {
            userAdapter.submitList(it)
        }
    }

    private fun addUser(user: User) {
        userViewModel.addUser(user)
    }
}