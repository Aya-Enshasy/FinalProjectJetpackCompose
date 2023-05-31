package com.ayaenshasy.finalproject.activities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.launch

@Composable
fun OrderDone(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.illustration),
            "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        Row(Modifier.fillMaxWidth().padding(bottom =16.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "ORDER",
                Modifier
                    .padding(horizontal = 2.dp),color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center,)
            Text(text = "Done",
                Modifier
                    .padding(horizontal = 2.dp),color = colorResource(id = R.color.blue),
                fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center,)
            Text(text = "!",
                Modifier
                    .padding(horizontal = 2.dp),color = colorResource(id = R.color.orange),
                fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center,)


        }

        Text(text = "The ORDER has been sent. A technician will \n contact you",
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),color = colorResource(id = R.color.black), fontSize = 18.sp, textAlign = TextAlign.Center,)


        Spacer(modifier = Modifier.padding(50.dp))

        GradientButton1 {
                     navController.navigate("main_screen")

        }

    }

}
@Composable
fun GradientButton1(onClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreData(context)


    Button(
        modifier = Modifier
            .fillMaxWidth().height(48.dp)
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

            modifier = Modifier.height(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.light_blue),
                            colorResource(id = R.color.blue),
                        )
                    ),
                ).fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Go To Home", color = Color.White)
        }
    }
}
