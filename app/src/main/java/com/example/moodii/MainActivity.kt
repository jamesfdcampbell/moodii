package com.example.moodii

import com.example.moodii.moods.MoodMenu
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
import com.example.moodii.therapists.*
import com.example.moodii.ui.theme.MoodiiTheme
import com.example.moodii.ui.theme.comfortaa
import com.example.moodii.utilities.readCsv
import com.example.moodii.quotes.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moodii.moods.DisplayMoodHistory
import com.example.moodii.moods.database.AppDatabase
import com.example.moodii.moods.NewMood

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val therapists = readCsv(resources.openRawResource(R.raw.therapists))
            val db = MyApp.database

            MoodiiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(therapists, db)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(therapists: List<Therapist>, db: AppDatabase) {
    val navController = rememberNavController()
    val quoteViewModel: QuoteViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavBar.Moods.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavBar.Moods.route) {
                MoodMenu(navController)
            }
            composable(BottomNavBar.Therapists.route) {
                TherapistList(therapists = therapists)
            }
            composable(BottomNavBar.Quotes.route) {
                QuotesDisplay(quotes = quoteViewModel.quotes.value, onNewQuoteClicked = { quoteViewModel.newQuote()})
            }
            composable("newMood") {
                NewMood(db = db)
            }
            composable("moodList") {
                DisplayMoodHistory()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define background colors for different routes
    val defaultBackgroundColor = MaterialTheme.colorScheme.primary
    val therapistsBackgroundColor = Color(0xFFcd5b45)
    val quotesBackgroundColor = Color(0xFF006f83)
    val contentColor = MaterialTheme.colorScheme.onPrimary

    // Determine the background color based on the current route
    val backgroundColor = if (currentRoute == BottomNavBar.Quotes.route) {
        quotesBackgroundColor
    } else if (currentRoute == BottomNavBar.Therapists.route) {
        therapistsBackgroundColor
    } else {
        defaultBackgroundColor
    }

    // Create the bottom navigation bar with items and colors
    BottomNavigation(
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.moodtracking_icon),
                    contentDescription = "An icon for Mood Tracking, depicted by a small image of a calendar.",
                    tint = contentColor
                )
            },
            label = {
                Text(
                    "Moods",
                    color = Color.White,
                    fontFamily = comfortaa
                )
            },
            selected = navController.currentDestination?.route == BottomNavBar.Moods.route,
            onClick = { navController.navigate(BottomNavBar.Moods.route) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.therapist_icon),
                    contentDescription = "An icon for Therapists, depicted by an image of a head with a gear where the brain would be.",
                    tint = contentColor
                )
            },
            label = {
                Text(
                    "Therapists",
                    color = Color.White,
                    fontFamily = comfortaa
                )
            },
            selected = navController.currentDestination?.route == BottomNavBar.Therapists.route,
            onClick = { navController.navigate(BottomNavBar.Therapists.route) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.moodlifter_icon),
                    contentDescription = "An icon for Quotes, depicted by an image of a smiling face.",
                    tint = contentColor
                )
            },
            label = {
                Text(
                    "Quotes",
                    color = Color.White,
                    fontFamily = comfortaa
                )
            },
            selected = navController.currentDestination?.route == BottomNavBar.Quotes.route,
            onClick = { navController.navigate(BottomNavBar.Quotes.route) }
        )
    }
}