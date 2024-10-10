package com.example.blogapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.model.BlogPost
import com.example.blogapp.repository.BlogRepository
import kotlinx.coroutines.launch

class BlogViewModel(private val repository: BlogRepository) : ViewModel(){
    private val _blogPosts = MutableLiveData<List<BlogPost>>()
    val blogPosts: LiveData<List<BlogPost>> get() = _blogPosts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchBlogs()
    }

    private fun fetchBlogs() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val blogs = repository.getBlogPosts()
                _blogPosts.value = blogs
            } catch (e: Exception) {
                _error.value = "Failed to load blogs"
            } finally {
                _loading.value = false
            }
        }
    }

}