package com.ayaenshasy.finalproject.navigation1

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayaenshasy.finalproject.activities.*
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel
import com.ayaenshasy.finalproject.viewModel.LoginViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun navigation(loginViewModel: LoginViewModel,liveDataAllWorkModel:AllWorkViewModel) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {

        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("main_screen") {
            MainScreen(liveDataAllWorkModel)
//            navController.popBackStack()
        }
        composable("onboard_screen") {
            OnboardingUI(navController = navController)
         }

        composable("signup_screen") {
            RegisterScreen(navController = navController,loginViewModel)
         }

        composable("login_screen") {
            LoginScreen(navController = navController,loginViewModel)
         }

        composable("orderDone_screen") {
            OrderDone(navController = navController)
         }

    }
}