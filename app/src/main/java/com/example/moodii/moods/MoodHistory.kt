package com.example.moodii.moods

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moodii.R
import com.example.moodii.moods.database.AppDatabase
import com.example.moodii.ui.theme.comfortaa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.moodii.utilities.formatTimestamp

@Composable
fun DisplayMoodHistory() {
    // Get the current context
    val context = LocalContext.current

    // Initialize the list of mood entries
    var itemList by remember { mutableStateOf(emptyList<MoodEntry>()) }

    // Create a coroutine scope
    val coroutine = rememberCoroutineScope()

    // Get a reference to the database
    val db = AppDatabase.getDatabase(LocalContext.current)

    // Use a LaunchedEffect to fetch mood data in the background
    LaunchedEffect(key1 = true) {
        // Fetch the data in IO context (background thread)
        val moods = withContext(Dispatchers.IO) {
            db.moodDao().getAllMoods()
        }
        // Update the list of mood entries
        itemList = moods
    }

    // Create a column for displaying the UI
    Column {
        // Display the Mood History title
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            tonalElevation = 3.dp
        ) {
            Text(
                text = "Mood History",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = comfortaa,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
        }

        // Create a LazyColumn for displaying mood entries
        LazyColumn {
            items(itemList) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // First Row: Display the timestamp
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = formatTimestamp(item.timestamp),
                            fontFamily = comfortaa,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 5.dp)
                                .padding(start = 2.dp)
                        )
                    }

                    // Second Row: Display mood image and text
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .padding(bottom = 10.dp)
                    ) {
                        // Mood Image Column
                        val moodImage = when (item.mood) {
                            "Very Happy" -> R.drawable.mood1
                            "Happy" -> R.drawable.mood2
                            "Neutral" -> R.drawable.mood3
                            "Unhappy" -> R.drawable.mood4
                            "Very Unhappy" -> R.drawable.mood5
                            else -> R.drawable.mood3 // Default or fallback image
                        }
                        Image(
                            painter = painterResource(id = moodImage),
                            contentDescription = item.mood,
                            modifier = Modifier.weight(1f) // Less weight for image
                        )

                        // Mood Text Column
                        Text(
                            text = item.mood,
                            fontFamily = comfortaa,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(5f), // More weight for text
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    // Third Row: Display mood details
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = item.moodDetails,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}
