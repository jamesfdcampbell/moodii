package com.example.moodii.navbar

import com.example.moodii.R

sealed class BottomNavBar(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Moods : BottomNavBar(
        route = "moods",
        title = "Moods",
        icon = R.drawable.moodtracking_icon
    )

    object Therapists : BottomNavBar(
        route = "therapists",
        title = "Therapists",
        icon = R.drawable.therapist_icon
    )

    object Quotes : BottomNavBar(
        route = "quotes",
        title = "Quotes",
        icon = R.drawable.moodlifter_icon
    )

//    companion object {
//        fun values() = listOf(Moods, Therapists, Quotes)
//    }
}
