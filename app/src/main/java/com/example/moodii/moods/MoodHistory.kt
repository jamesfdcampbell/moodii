package com.example.moodii.moods

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodii.moods.database.AppDatabase
import com.example.moodii.therapists.Therapist
import com.example.moodii.ui.theme.comfortaa
//import com.example.moodii.moods.database.MoodViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
//fun MoodListScreen(viewModel: MoodViewModel) {
//    val moods by viewModel.moods.observeAsState(initial = emptyList())
//
//    LazyColumn {
//        items(moods) { mood ->
//            Text(text = "Mood: ${mood.mood}, Details: ${mood.moodDetails}, Date: ${mood.timestamp}")
//        }
//    }
//}
fun DisplayMoodHistory() {
    val context = LocalContext.current
    var itemList by remember { mutableStateOf(emptyList<MoodEntry>()) }

    val coroutine = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(LocalContext.current)

    LaunchedEffect(key1 = true) {
        // Fetch the data in IO context (background thread)
        val moods = withContext(Dispatchers.IO) {
            db.moodDao().getAllMoods()
        }
        itemList = moods
    }

    LazyColumn {
        items(itemList) { item ->
            Row (verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = item.mood, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    Text(text = item.moodDetails, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                }
            }

        }
    }
}