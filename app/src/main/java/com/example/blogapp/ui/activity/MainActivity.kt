package com.example.blogapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.blogapp.local.BlogDatabase
import com.example.blogapp.repository.BlogRepository
import com.example.blogapp.network.Api
import com.example.blogapp.ui.screen.BlogScreen
import com.example.blogapp.viewmodel.BlogViewModel
import com.example.blogapp.viewmodel.BlogViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize your BlogDatabase and BlogRepository
        val blogDatabase = BlogDatabase.getDatabase(application)

        val blogApiService = Api.retrofitService

        val blogRepository = BlogRepository(blogApiService, blogDatabase.blogDao())
        // Use the ViewModelFactory to create the BlogViewModel
        val viewModel = ViewModelProvider(
            this,
            BlogViewModelFactory(blogRepository) // Pass repository to factory
        )[BlogViewModel::class.java]

        // Set the content to your BlogScreen
        setContent {
            BlogScreen(viewModel)
        }
    }
}

