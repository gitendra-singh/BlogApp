package com.example.blogapp.helpers

import androidx.room.TypeConverter
import com.example.blogapp.model.RenderedText
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromRenderedText(renderedText: RenderedText?): String {
        return Gson().toJson(renderedText) // Convert RenderedText to a JSON string
    }

    @TypeConverter
    fun toRenderedText(renderedTextString: String): RenderedText? {
        return Gson().fromJson(renderedTextString, RenderedText::class.java) // Convert JSON string back to RenderedText
    }
}
