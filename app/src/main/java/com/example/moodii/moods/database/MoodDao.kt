package com.example.moodii.moods.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moodii.moods.MoodEntry

// Data Access Object (DAO) for interacting with the mood_table
@Dao
interface MoodDao {

    // Insert a new MoodEntry into the database
    @Insert
    fun insertMood(moodEntry: MoodEntry)

    // Retrieve all MoodEntries from the database, sorted by their ID in descending order
    @Query("SELECT * FROM mood_table order by id DESC")
    fun getAllMoods(): List<MoodEntry>
}
