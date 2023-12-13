//package com.example.moodii.moods.database
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import com.example.moodii.moods.MoodEntry
//
////class MoodViewModel : ViewModel() {
////    private val moodDao by lazy {
////        val db = MyApp
////        db.moodDao()
////    }
////
////    val moods: LiveData<List<MoodEntry>> = moodDao.getAllMoods().asLiveData()
////}
//
//class MoodViewModel(private val repository: MoodRepository): ViewModel() {
//    val moods: List<MoodEntry> = repository.getAllMoods()
//}