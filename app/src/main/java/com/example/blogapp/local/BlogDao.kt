package com.example.blogapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogapp.model.BlogPost

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogPosts: List<BlogPost>)

    @Query("SELECT * FROM blogpost")
    suspend fun getAllBlogs(): List<BlogPost>
}