package com.imceits.aungtuntun.theomdbmovie.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CastListTypeConverter {

    @TypeConverter
    fun fromString(value: String?) : List<Cast>? {
        val listType = object : TypeToken<List<Cast>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Cast>?): String? {
        return Gson().toJson(list)
    }
}