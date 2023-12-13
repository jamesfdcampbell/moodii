package com.example.moodii.moods

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodii.R
import com.example.moodii.moods.database.AppDatabase
import com.example.moodii.ui.theme.comfortaa

@Composable
fun NewMood(db: AppDatabase) {
    // Get the current context
    val context = LocalContext.current

    // Define mood options and their corresponding images
    val moods = listOf("Very Happy", "Happy", "Neutral", "Unhappy", "Very Unhappy")
    val moodImages = listOf(
        painterResource(id = R.drawable.mood1),
        painterResource(id = R.drawable.mood2),
        painterResource(id = R.drawable.mood3),
        painterResource(id = R.drawable.mood4),
        painterResource(id = R.drawable.mood5)
    )

    // Initialize selected mood and mood details
    var selectedMood by remember { mutableStateOf(moods.first()) }
    var moodDetails by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Display the "How are you feeling?" title
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            color = MaterialTheme.colorScheme.primary,
            tonalElevation = 3.dp,
        ) {
            Text(
                text = "How are you feeling?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = comfortaa,
                    color = Color.White
                ),
                modifier = Modifier.padding(16.dp)
            )
        }

        // Create radio buttons for mood selection
        moods.forEachIndexed { index, mood ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RadioButton(
                    selected = mood == selectedMood,
                    onClick = { selectedMood = mood },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Image(
                    painter = moodImages[index],
                    contentDescription = mood,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = mood,
                    Modifier.padding(start = 8.dp),
                    fontSize = 18.sp)
            }
        }

        // Create a text field for mood details
        TextField(
            value = moodDetails,
            onValueChange = { moodDetails = it },
            textStyle = TextStyle(
                fontSize = 20.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(285.dp) // Larger text area
                .padding(16.dp),
            label = { Text("Mood Details") }
        )

        // Create a button to save the mood entry
        Button(
            onClick = {
                val moodEntry = MoodEntry(mood = selectedMood, moodDetails = moodDetails)
                Thread {
                    db.moodDao().insertMood(moodEntry)
                }.start()
                Toast.makeText(context, "New mood saved", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Save Mood",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = comfortaa)
        }
    }
}
