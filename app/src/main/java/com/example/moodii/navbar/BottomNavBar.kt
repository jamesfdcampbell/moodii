package com.example.moodii.navbar

import com.example.moodii.R

// Define a sealed class for the bottom navigation bar items
sealed class BottomNavBar(
    val route: String,   // Route to navigate to when the item is selected
    val title: String,   // Display title of the navigation item
    val icon: Int        // Resource ID of the icon associated with the navigation item
) {
    // Define an object for the "Moods" navigation item
    object Moods : BottomNavBar(
        route = "moods",
        title = "Moods",
        icon = R.drawable.moodtracking_icon
    )

    // Define an object for the "Therapists" navigation item
    object Therapists : BottomNavBar(
        route = "therapists",
        title = "Therapists",
        icon = R.drawable.therapist_icon
    )

    // Define an object for the "Quotes" navigation item
    object Quotes : BottomNavBar(
        route = "quotes",
        title = "Quotes",
        icon = R.drawable.moodlifter_icon
    )
}