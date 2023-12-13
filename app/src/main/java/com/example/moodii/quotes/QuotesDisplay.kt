package com.example.moodii.quotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moodii.R
import com.example.moodii.ui.theme.comfortaa

@Composable
fun QuotesDisplay(quotes: List<Quote>?, onNewQuoteClicked: () -> Unit) {
    // Load background image
    val backgroundPainter = painterResource(id = R.drawable.quotesbackground)

    // Create a box to overlay the background image
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundPainter,
            contentDescription = "Background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Black.copy(alpha = 0.2f)
        ) {
            Surface(
                modifier = Modifier
                    .padding(16.dp),
                color = Color.Transparent
            ) {
                if (!quotes.isNullOrEmpty()) {
                    // Display a quote and author if available
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(bottom = 155.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "\"${quotes.first().quote}\"",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White,
                                fontFamily = comfortaa,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "- ${quotes.first().author}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                fontFamily = comfortaa,
                                textAlign = TextAlign.Center
                            )
                        }
                        // Button to load a new quote
                        Button(
                            onClick = onNewQuoteClicked,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006f83)),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 30.dp)
                        ) {
                            Text("Load New Quote",
                                fontFamily = comfortaa)
                        }
                    }
                } else {
                    // If quotes are not available, show a loading indicator
                    CircularProgressIndicator()
                }
            }
        }
    }
}