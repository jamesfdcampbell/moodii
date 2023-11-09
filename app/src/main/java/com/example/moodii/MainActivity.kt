package com.example.moodii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodii.ui.theme.MoodiiTheme
import java.io.InputStream
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : ComponentActivity() {

    data class Therapist(
        val name: String,
        val title: String,
        val description: String,
        val phone: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun readCsv(inputStream: InputStream): List<Therapist> {
            val reader = inputStream.bufferedReader()
            val header = reader.readLine()
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

        val therapists = readCsv(resources.openRawResource(R.raw.therapists))

        setContent {
            MoodiiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TherapistList(therapists = therapists)
                }
            }
        }
    }
}

val comfortaa = FontFamily(Font(R.font.comfortaa_regular))

@Composable
fun TherapistList(therapists: List<MainActivity.Therapist>) {
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {}
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Therapists,
        BottomBarScreen.MoodTracking,
        BottomBarScreen.MoodLifters
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

}

@Composable
fun TherapistItem(therapist: MainActivity.Therapist) {
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
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
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

val sampleTherapists = listOf(
    MainActivity.Therapist(
        "Jennifer N Schultz",
        "Counsellor, MA",
        "Are you worried?",
        "431-301-4321"
    ),
    MainActivity.Therapist(
        "Debbie Lynn Tabin",
        "Registered Social Worker, BSW, RSW, MAL",
        "Do you find it hard?",
        "null"
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MoodiiTheme {
        TherapistList(therapists = sampleTherapists)
    }
}
