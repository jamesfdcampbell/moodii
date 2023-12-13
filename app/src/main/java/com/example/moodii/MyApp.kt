package com.example.moodii

import android.app.Application
import androidx.room.Room
import com.example.moodii.moods.database.AppDatabase

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "moodii-database"
        ).build()
    }

    companion object {
        lateinit var database: AppDatabase
    }
}