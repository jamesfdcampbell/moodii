package com.example.moodii

import android.app.Application
import androidx.room.Room
import com.example.moodii.moods.database.AppDatabase

// Custom Application class for initializing the database
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize the database using Room database builder
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "moodii-database"
        ).build()
    }

    companion object {
        // Companion object to provide a reference to the database instance
        lateinit var database: AppDatabase
    }
}