package com.ayaenshasy.finalproject.fragment

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayaenshasy.finalproject.fragment.screens.*
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel
@Composable
fun TopNavigation(navController: NavHostController,liveDataAllWorkModel:AllWorkViewModel,context: Context) {

    NavHost(navController = navController, startDestination = "pending") {
        composable("pending") {
            PendingScreen(liveDataAllWorkModel, context )
        }
        composable("underway") {
            UnderwayScreen(liveDataAllWorkModel)
        }
        composable("completed") {
            CompletedScreen(liveDataAllWorkModel)
        }

    }
}
