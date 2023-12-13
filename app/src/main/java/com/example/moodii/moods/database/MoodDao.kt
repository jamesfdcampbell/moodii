package com.example.moodii.moods.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moodii.moods.MoodEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert
    fun insertMood(moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_table order by id DESC")
    fun getAllMoods(): List<MoodEntry>
}