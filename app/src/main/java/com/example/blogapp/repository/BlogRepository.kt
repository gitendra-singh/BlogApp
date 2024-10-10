package com.example.blogapp.repository

import com.example.blogapp.local.BlogDao
import com.example.blogapp.model.BlogPost
import com.example.blogapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class BlogRepository(
    private val blogApiService: ApiService,
    private val blogDao: BlogDao
) {
    suspend fun getBlogPosts(): List<BlogPost> {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch from the API
                val blogPosts = blogApiService.getBlogs().map {post ->
                    post.copy(
                        guid = post.guid,
                        title = post.title,
                        content = post.content
                    )

                }


                // Cache the data in the local database
                blogDao.insertBlogs(blogPosts)

                // Return the data
                blogPosts
            } catch (e: IOException) {
                println("Network error: ${e.message}")
                // On failure, return cached data
                blogDao.getAllBlogs()
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
                blogDao.getAllBlogs() // Return cached data
            }
        }

    }
}