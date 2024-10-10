package com.example.blogapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blogapp.helpers.Converters
import com.example.blogapp.model.BlogPost

@Database(entities = [BlogPost::class], version = 1)
@TypeConverters(Converters::class)
abstract class BlogDatabase: RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        @Volatile
        private var INSTANCE: BlogDatabase? = null

        fun getDatabase(context: Context): BlogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlogDatabase::class.java,
                    "blog_database" // Name of your database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}