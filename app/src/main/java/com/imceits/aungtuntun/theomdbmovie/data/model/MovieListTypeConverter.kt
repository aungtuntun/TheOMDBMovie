package com.imceits.aungtuntun.theomdbmovie.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imceits.aungtuntun.theomdbmovie.data.Movies

class MovieListTypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<Movies>? {
        val listType = object: TypeToken<List<Movies>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(movies: List<Movies>?): String? {
        return Gson().toJson(movies)
    }
}