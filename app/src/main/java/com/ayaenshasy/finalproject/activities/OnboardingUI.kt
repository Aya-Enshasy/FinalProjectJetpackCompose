package com.ayaenshasy.finalproject.activities

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.datastore.StoreData
import com.ayaenshasy.finalproject.model.Page
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun OnboardingUI(
    navController: NavController) {
    val context = LocalContext.current
    saveIsFirstTime(true,context)

    val pagerState = rememberPagerState(pageCount = 3)

    Column(modifier = Modifier.background(Color.White)) {

        HorizontalPager(state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) ) { page ->

            PageUI(page = onboardPages[page],navController,pagerState)
        }

        HorizontalPagerIndicator(pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = colorResource(R.color.blue)
        )

    }
}


val onboardPages = listOf(
    Page(
        "Fast reservation with technicians \n" +
                "and craftsmen",
         R.drawable.mobile_application_call_taxi_vector_illustration_82574_3185
    ),
    Page(
        "Fast reservation with technicians \n" +
                "and craftsmen",
        R.drawable.mobile_application_call_taxi_vector_illustration_82574_3185
    ),
    Page(
        "Fast reservation with technicians \n" +
                "and craftsmen",
        R.drawable.mobile_application_call_taxi_vector_illustration_82574_3185
    )
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PageUI(page: Page, navController: NavController, pagerState:PagerState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.mobile_application_call_taxi_vector_illustration_82574_3185),
            "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )


        Text(text = "Fast reservation with technicians \n" + "and craftsmen",
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),color = colorResource(id = R.color.text), fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center,)


        Spacer(modifier = Modifier.padding(50.dp))

        AnimatedVisibility(visible = pagerState.currentPage == 2) {
            GradientButton {
                navController.popBackStack()
                navController.navigate("login_screen")

            }
        }

    }


}




@Composable
fun GradientButton(onClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreData(context)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick()
            scope.launch {
                dataStore.saveIsFirstTime("true")
            }
        })
    {
        Box(

            modifier = Modifier
                .height(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.light_blue),
                            colorResource(id = R.color.blue),
                        )
                    ),
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Next", color = Color.White)
        }
    }
}


fun saveIsFirstTime(isFirstTime: Boolean, context: Context) {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPrefs.edit()
    editor.putBoolean("onBoarding", isFirstTime)
    editor.apply()
}