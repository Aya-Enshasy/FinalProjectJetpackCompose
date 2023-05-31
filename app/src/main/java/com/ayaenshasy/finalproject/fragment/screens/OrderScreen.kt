@file:OptIn(ExperimentalMaterialApi::class)

package com.ayaenshasy.finalproject.fragment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.fragment.Navigation1
import com.ayaenshasy.finalproject.fragment.TopNavigation
import com.ayaenshasy.finalproject.model.BottomNavItem
import com.ayaenshasy.finalproject.model.BottomNavItem2
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel


@Composable
fun OrderScreen(liveDataAllWorkModel:AllWorkViewModel) {
    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        topBar()
        Scaffold(
            topBar = {
                TopNavigationBar(
                    items = listOf(
                        BottomNavItem2(
                            name = "Pending",
                            route = "pending",

                        ),
                        BottomNavItem2(
                            name = "Underway",
                            route = "underway",
                         ),
                        BottomNavItem2(
                            name = "Completed",
                            route = "completed",
                         ),

                    ),
                    navController = navController
                ) {
                    navController.navigate(it.route)
                }
            }
        ) {
            TopNavigation(navController,liveDataAllWorkModel,context)
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun TopNavigationBar(
    items: List<BottomNavItem2>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem2) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier.height(70.dp),
        backgroundColor =  Color.White,
        elevation = 0.dp
    ) {
        items.forEach { item1 ->
            val selected = item1.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item1) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,

                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if (selected){
                            Text(
                                text = item1.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontSize = 14.sp
                            )
                        }else{
                            Text(
                                text = item1.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }


                }
            )
        }
    }
}



@Composable
fun topBar( ) {
    TopAppBar(
        modifier = Modifier.background( brush = Brush.horizontalGradient(
            colors = listOf(
                colorResource(id = R.color.light_blue),
                colorResource(id = R.color.blue),
            ))),
        title = { Text(text = "Item Details", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), color = Color.White)},
        backgroundColor = Color.Transparent,
        elevation = 0.dp

    )
}