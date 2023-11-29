package com.example.moodii.utilities
import com.example.moodii.therapists.Therapist
import java.io.InputStream

// Function to access data from CSV file of therapists
fun readCsv(inputStream: InputStream): List<Therapist> {
    val reader = inputStream.bufferedReader()
    val header = reader.readLine() // reads headers; header data not used
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .mapNotNull { line ->
            val fields = line.split('|', limit = 4)
            if (fields.size == 4) {
                val (name, title, description, phone) = fields
                Therapist(name, title, description, phone)
            } else {
                null
            }
        }.toList()
}