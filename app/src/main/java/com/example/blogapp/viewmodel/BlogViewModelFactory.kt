package com.example.blogapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blogapp.repository.BlogRepository

class BlogViewModelFactory(private val repository: BlogRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST") // Suppress the unchecked cast warning
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            return BlogViewModel(repository) as T // Safe cast
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
