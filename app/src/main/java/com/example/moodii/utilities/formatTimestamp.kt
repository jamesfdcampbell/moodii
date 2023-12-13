package com.example.moodii.utilities

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Function to format a Unix epoch timestamp into a human-readable string
fun formatTimestamp(timestamp: Long): String {
    // Define the date format using SimpleDateFormat
    val dateFormat = SimpleDateFormat("MMM dd, yyyy | h:mm:ss a", Locale.getDefault())

    // Create a Date object from the provided timestamp
    val date = Date(timestamp)

    // Format the date using the defined format and return the formatted string
    return dateFormat.format(date)
}