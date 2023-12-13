package com.example.moodii.moods.database

import com.example.moodii.moods.MoodEntry
import kotlinx.coroutines.flow.Flow

class MoodRepository(private val moodDao: MoodDao) {
    fun getAllMoods(): List<MoodEntry> = moodDao.getAllMoods()
}