package com.imceits.aungtuntun.theomdbmovie.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.imceits.aungtuntun.theomdbmovie.data.model.Cast

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun saveMovies(movies: List<Movies>)

    @Insert(onConflict = REPLACE)
    fun insertMovie(movies: Movies)

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): LiveData<List<Movies>>

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movies>

    @Query("UPDATE Movies SET casts = :list WHERE id = :id")
    fun updateMovieCast(list: List<Cast>, id: Int)
}