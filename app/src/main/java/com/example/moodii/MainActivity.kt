package com.example.moodii

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodii.navbar.BottomNavBar
import com.example.moodii.therapists.Therapist
import com.example.moodii.therapists.TherapistList
import com.example.moodii.ui.theme.MoodiiTheme
import com.example.moodii.ui.theme.comfortaa
import com.example.moodii.utilities.readCsv

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val therapists = readCsv(resources.openRawResource(R.raw.therapists))

        setContent {
            MoodiiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(therapists)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(therapists: List<Therapist>) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavBar.Therapists.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavBar.Moods.route) { }
            composable(BottomNavBar.Therapists.route) { 
                TherapistList(therapists = therapists)
            }
            composable(BottomNavBar.Quotes.route) {  }
        }
    }
}

//@Composable
//fun MoodsScreen() {
//    // Define the UI for the Moods screen
//}
//
//@Composable
//fun TherapistsScreen() {
//    // Define the UI for the Therapists screen
//}
//
//@Composable
//fun QuotesScreen() {
//    // Define the UI for the Quotes screen
//}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    val contentColor = MaterialTheme.colorScheme.onPrimary

    BottomNavigation(
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        BottomNavigationItem(
            icon = { Icon(
                painter = painterResource(id = R.drawable.moodtracking_icon),
                contentDescription = "An icon for Mood Tracking, depicted by a small image of a calendar.",
                tint = contentColor) },
            label = { Text("Moods",
                color = Color.White,
                fontFamily = comfortaa) },
            selected = navController.currentDestination?.route == BottomNavBar.Moods.route,
            onClick = { navController.navigate(BottomNavBar.Moods.route) }
        )
        BottomNavigationItem(
            icon = { Icon(
                painter = painterResource(id = R.drawable.therapist_icon),
                contentDescription = "An icon for Therapists, depicted by an image of a head with a gear where the brain would be.",
                tint = contentColor) },
            label = { Text("Therapists",
                color = Color.White,
                fontFamily = comfortaa) },
            selected = navController.currentDestination?.route == BottomNavBar.Therapists.route,
            onClick = { navController.navigate(BottomNavBar.Therapists.route) }
        )
        BottomNavigationItem(
            icon = { Icon(
                painter = painterResource(id = R.drawable.moodlifter_icon),
                contentDescription = "An icon for Quotes, depicted by an image of a smiling face.",
                tint = contentColor) },
            label = { Text("Quotes",
                color = Color.White,
                fontFamily = comfortaa) },
            selected = navController.currentDestination?.route == BottomNavBar.Quotes.route,
            onClick = { navController.navigate(BottomNavBar.Quotes.route) }
        )
    }
}


// Bottom Nav Bar -- In progress, for future implementation
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    Scaffold(bottomBar = {}
//    ) {
//        BottomNavGraph(navController = navController)
//    }
//}

//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        BottomBarScreen.Therapists,
//        BottomBarScreen.MoodTracking,
//        BottomBarScreen.MoodLifters
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//}


//// Sample data for preview purposes
//val sampleTherapists = listOf(
//    MainActivity.Therapist(
//        "Jennifer N Schultz",
//        "Counsellor, MA",
//        "Are you worried?",
//        "431-301-4321"
//    ),
//    MainActivity.Therapist(
//        "Debbie Lynn Tabin",
//        "Registered Social Worker, BSW, RSW, MAL",
//        "Do you find it hard?",
//        "null"
//    )
//)
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    MoodiiTheme {
//        TherapistList(therapists = sampleTherapists)
//    }
//}
