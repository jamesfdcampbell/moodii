package com.example.moodii.therapists

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.moodii.ui.theme.comfortaa

// Composable for therapist list, which is comprised of TherapistItems
@Composable
fun TherapistList(therapists: List<Therapist>) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0xFFcd5b45),
            tonalElevation = 3.dp
        ) {
            Text(
                text = "Local Therapists",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = comfortaa,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        // LazyColumn for scrolling content
        LazyColumn {
            items(therapists) { therapist ->
                TherapistItem(therapist)
            }
        }
    }
}

// Composable for each item in the therapists list
@Composable
fun TherapistItem(therapist: Therapist) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = therapist.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, fontFamily = comfortaa)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = therapist.title, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(8.dp))

            // Adding the horizontal line here
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = therapist.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))

            if (therapist.phone != "" && therapist.phone != "null") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone icon")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = therapist.phone,
                        style = MaterialTheme.typography.bodyLarge.merge(
                            TextStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold
                            )
                        ),
                        modifier = Modifier.clickable {
                            // Using Intent to start the phone dialer
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${therapist.phone}")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
            else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone icon")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "N/A", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}