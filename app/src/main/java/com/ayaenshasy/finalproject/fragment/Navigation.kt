package com.ayaenshasy.finalproject.fragment

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayaenshasy.finalproject.fragment.screens.*
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel

@Composable
fun Navigation1(navController: NavHostController,liveDataAllWorkModel:AllWorkViewModel) {

    NavHost(navController = navController, startDestination = "service") {
        composable("service") {
            HomeScreen(liveDataAllWorkModel)
        }
        composable("order") {
            OrderScreen(liveDataAllWorkModel)
        }
        composable("user") {
            SettingsScreen(navController)
        }
        composable("more") {
            MoreScreen(navController)
        }
    }
}
