package com.example.moodii.moods

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moodii.ui.theme.comfortaa

@Composable
fun MoodMenu(navController: NavController) {
    // Define button shape
    val buttonShape: Shape = RoundedCornerShape(60.dp)

    // Create a vertical column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button for recording a new mood
        Button(
            onClick = { navController.navigate("newMood") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 125.dp),
            shape = buttonShape
        ) {
            Text(
                "Record a New Mood",
                textAlign = TextAlign.Center,
                fontSize = 26.sp,
                fontFamily = comfortaa,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        // Button for accessing mood history
        Button(
            onClick = { navController.navigate("moodList") },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 125.dp),
            shape = buttonShape
        ) {
            Text(
                "Mood History",
                textAlign = TextAlign.Center,
                fontSize = 26.sp,
                fontFamily = comfortaa,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}