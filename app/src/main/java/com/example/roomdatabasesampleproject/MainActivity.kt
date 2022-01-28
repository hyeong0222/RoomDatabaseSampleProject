package com.example.roomdatabasesampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabasesampleproject.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}