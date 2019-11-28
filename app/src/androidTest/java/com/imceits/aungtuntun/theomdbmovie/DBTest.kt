package com.imceits.aungtuntun.theomdbmovie

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.imceits.aungtuntun.theomdbmovie.data.MovieDatabase
import org.junit.After
import org.junit.Before
import java.util.concurrent.TimeUnit

abstract class DBTest {

    val countingTaskExecutorRule = CountingTaskExecutorRule()
    private lateinit var db: MovieDatabase
    val movieDB : MovieDatabase get() = db

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, MovieDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        db.close()
    }
}