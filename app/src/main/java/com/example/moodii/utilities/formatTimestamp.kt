package com.example.moodii.utilities

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Formats the Unix epoch timestamp in the given format
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy | h:mm:ss a", Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}