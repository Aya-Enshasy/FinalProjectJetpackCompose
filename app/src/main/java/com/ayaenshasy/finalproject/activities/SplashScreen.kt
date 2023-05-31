package com.ayaenshasy.finalproject.activities

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.datastore.StoreData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import java.util.prefs.Preferences

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(3000L)
        getIsFirstTime(context)
        Log.e("token",getAuthToken(context).toString())

        if (!getAuthToken(context).equals("") && getIsFirstTime(context) == true)
            navController.navigate("main_screen")
        else if (getAuthToken(context).equals("") && getIsFirstTime(context) == false)
            navController.navigate("onboard_screen")
        else
            navController.navigate("login_screen")


    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(
                            R.color.blue
                        ), colorResource(R.color.light_blue)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        )
    }


}

fun getAuthToken(context: Context): String? {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPrefs.getString("auth_token", null)
}

fun getIsFirstTime(context: Context): Boolean? {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPrefs.getBoolean("onBoarding", false)
}

