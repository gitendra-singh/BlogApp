package com.example.blogapp.model

import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.google.gson.annotations.SerializedName

@Entity(tableName = "blogpost")
data class BlogPost(
    @PrimaryKey val id: Int,
    val date: String,
    @SerializedName("date_gmt") val dateGmt: String,
    @TypeConverters(Converters::class) val guid: RenderedText,
    val modified: String,
    @SerializedName("modified_gmt") val modifiedGmt: String,
    val slug: String,
    val status: String,
    val type: String,
    val link: String,
    @TypeConverters(Converters::class) val title: RenderedText, // Register type converter
    @TypeConverters(Converters::class) val content: RenderedText // Register type converter
)

data class RenderedText(
    @SerializedName("rendered") val rendered: String
)
