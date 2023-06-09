package com.ayaenshasy.finalproject.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ayaenshasy.finalproject.model.BottomNavItem

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier.height(70.dp),
        backgroundColor =  Color("#6FC8FB".toColorInt()),
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {


                        Image(painter = painterResource(id = item.icon),
                            contentDescription ="",
                            modifier
                                .padding(PaddingValues(top = 6.dp))
                                .width(20.dp)
                                .height(20.dp),

                            )

                        if (selected){
                            Text(
                                text = item.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontSize = 14.sp
                            )
                        }else{
                            Text(
                                text = item.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }


                }
            )
        }
    }
}