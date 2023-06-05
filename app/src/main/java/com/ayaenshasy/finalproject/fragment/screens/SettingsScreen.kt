package com.ayaenshasy.finalproject.fragment.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.model.Profile1
import kotlinx.coroutines.launch
import okhttp3.internal.wait


@Composable
fun SettingsScreen(navController: NavController) {
    AccountScreen(navController)
}

@Composable
fun AccountScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(
                            R.color.blue
                        ), colorResource(R.color.light_blue)
                    )
                )
            ),
        horizontalAlignment = Alignment.End,
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 85.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = getName(context).toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 10.dp)
                )
            }

            Text(
                text = getEmail(context).toString(),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 130.dp, bottom = 20.dp),
                fontSize = 16.sp
            )

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            elevation = 4.dp,
        ) {
            LazyColumn {
                items(more1) { post ->
                    MoreCard1(post)
                }
            }
        }

        ButtonWithIcon(context, navController)
    }

    RoundCornerImageView()


}

@Composable
fun RoundCornerImageView() {
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = "Round corner image",
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth()
            .padding(top = 50.dp, start = 120.dp, end = 120.dp)
            .clip(RoundedCornerShape(10))
            .border(5.dp, Color.White, RoundedCornerShape(10))
    )
}

val more1 = listOf(
    Profile1(name = "Language", "English"),
    Profile1(name = "My Rates", ""),
    Profile1(name = "Contact us", ""),
    Profile1(name = "Share App", ""),
)

@Composable
fun MoreCard1(post: Profile1) {
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

                    Row() {
                        Text(
                            text = post.details,
                            color = colorResource(id = android.R.color.system_accent2_500),
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

}

@Composable
fun ButtonWithIcon(context: Context, navController: NavController) {

    Button(
        onClick = {
            (context as? Activity)?.finish()
//            navController.navigate("login_screen")
             val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sharedPrefs.edit()
            editor.putString("auth_token", "")
             editor.apply()

        }, modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 16.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 7.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Image(
            painterResource(id = R.drawable.ic_join),
            contentDescription = "SIGN OUT", modifier = Modifier.size(30.dp)
        )

        Text(
            text = "SIGN OUT",
            color = colorResource(id = R.color.blue),
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 18.sp
        )
    }
}


fun getName(context: Context): String? {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPrefs.getString("name", null)
}

fun getEmail(context: Context): String? {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPrefs.getString("email", null)
}
