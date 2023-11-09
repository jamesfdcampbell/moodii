package com.example.moodii

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object MoodTracking : BottomBarScreen(
        route = "moodtracking",
        title = "Mood Tracking",
        icon = R.drawable.moodtracking_icon
    )

    object Therapists : BottomBarScreen(
        route = "therapists",
        title = "Therapists",
        icon = R.drawable.therapist_icon
    )

    object MoodLifters : BottomBarScreen(
        route = "moodlifters",
        title = "Mood Lifters",
        icon = R.drawable.moodlifter_icon
    )
}
