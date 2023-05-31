package com.ayaenshasy.finalproject.activities

import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.fragment.BottomNavigationBar
import com.ayaenshasy.finalproject.fragment.Navigation1
import com.ayaenshasy.finalproject.model.BottomNavItem
import com.ayaenshasy.finalproject.navigation1.navigation
import com.ayaenshasy.finalproject.ui.theme.FinalProjectTheme
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
 @Composable
fun MainScreen(liveDataAllWorkModel:AllWorkViewModel) {

    FinalProjectTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Service",
                            route = "service",
                            icon = R.drawable.group_10517
                        ),
                        BottomNavItem(
                            name = "Order",
                            route = "order",
                            icon = R.drawable.receipt,
                        ),
                        BottomNavItem(
                            name = "User",
                            route = "user",
                            icon = R.drawable.icons_5,
                        ),
                        BottomNavItem(
                            name = "More",
                            route = "more",
                            icon = R.drawable.ic_more_horiz_24px,
                        ),
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        ) {
            Navigation1(navController, liveDataAllWorkModel)
        }

    }
}