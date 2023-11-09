package com.example.moodii

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Therapists.route
    ) {
        composable(route = BottomBarScreen.Therapists.route){
            // left blank for future implementation
        }
        composable(route = BottomBarScreen.MoodLifters.route){
            // left blank for future implementation
        }
        composable(route = BottomBarScreen.MoodTracking.route){
            // left blank for future implementation
        }
    }
}