package com.example.moodii.moods.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moodii.moods.MoodEntry

// Define the Room database for the app
@Database(entities = [MoodEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Abstract method to get DAO for operations related to `MoodEntry`
    abstract fun moodDao(): MoodDao

    companion object {
        // Singleton instance of the database
        private var INSTANCE: AppDatabase? = null

        // Method to get the database instance
        fun getDatabase(context: Context): AppDatabase {
            // If the INSTANCE is null, then create the database instance
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    // Building the database with Room
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "moodii-database"
                    ).build()
                }
            }
            // Return the database instance
            return INSTANCE!!
        }
    }
}
