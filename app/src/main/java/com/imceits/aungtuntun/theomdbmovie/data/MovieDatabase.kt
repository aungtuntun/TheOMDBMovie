package com.imceits.aungtuntun.theomdbmovie.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imceits.aungtuntun.theomdbmovie.data.model.*

@Database(entities = [Movies::class], version = 1, exportSchema = false)
@TypeConverters(value = [StringListTypeConverter::class, CastListTypeConverter::class,
    GenreListTypeConverter::class, DateConverter::class, MovieListTypeConverter::class])
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}