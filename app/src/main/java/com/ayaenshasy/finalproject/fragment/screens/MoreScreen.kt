package com.ayaenshasy.finalproject.fragment.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.activities.SmithActivity
import com.ayaenshasy.finalproject.activities.smithTopBar
import com.ayaenshasy.finalproject.model.Post
import com.ayaenshasy.finalproject.model.Profile

@Composable
fun MoreScreen(navController: NavController) {

    Column(
        modifier = Modifier
         .background(colorResource(id = R.color.white))
         .fillMaxSize()
    ) {
        TopBar(navController)
        LazyColumn {
            items(more) { post ->
                MoreCard(post)
            }
        }
    }

}


val more = listOf(
    Profile(name = "Change Password"),
    Profile(name = "FAQ's"),
    Profile(name = "About App"),
    Profile(name = "Terms & Conditions"),
    Profile(name = "Privacy Policy"),
    Profile(name = "Rate App"),
    Profile(name = "Delete Account"),
)

@Composable
fun MoreCard(post: Profile) {
    Column(
     Modifier
      .fillMaxSize()
      .padding(top = 7.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 0.dp,
            backgroundColor = colorResource(id = R.color.white)
        ) {
            Column(Modifier.fillMaxWidth()) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp)
                ) {
                    Text(
                        text = post.name,
                        color = colorResource(id = R.color.black),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = " ",
                        tint = Color.Gray
                    )

                }


            }
        }
    }

}

@Composable
fun TopBar(navController:NavController) {

    Box(
        modifier = with(Modifier) {
         fillMaxWidth()
          .paint(
           painterResource(id = R.drawable.background),
           contentScale = ContentScale.FillBounds
          )
          .height(100.dp)

        })
    {
     smithTopBar("More",navController)
    }
}

