package com.imceits.aungtuntun.theomdbmovie.data.model

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long?) : Date? {
        return timestamp?.let{Date(it)}
    }

    @TypeConverter
    fun fromDate(date: Date?) : Long? {
        return date?.time?.toLong()
    }
}