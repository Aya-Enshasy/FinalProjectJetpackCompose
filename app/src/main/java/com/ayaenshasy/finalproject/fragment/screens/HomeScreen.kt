package com.ayaenshasy.finalproject.fragment.screens

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaenshasy.finalproject.R
 import com.ayaenshasy.finalproject.activities.SmithActivity
import com.ayaenshasy.finalproject.model.Data
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel


@Composable
fun HomeScreen(liveDataAllWorkModel: AllWorkViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.group_10517),
                        modifier = Modifier
                            .size(34.dp)
                            .weight(1f),
                        contentDescription = "notification icon"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.shape),
                        modifier = Modifier
                            .size(24.dp),
                        contentDescription = "notification icon"
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .padding(horizontal = 35.dp, vertical = 20.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                ) {

                }
            }

        }
        Text(
            text = "Selected Service",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp),
        )

        LazyColumn {
            items(liveDataAllWorkModel.liveDataAllWork.chunked(3)) { work ->
                GridItem(work)
            }}


        liveDataAllWorkModel.getAllWork()
    }
}


@Composable
fun GridItem(work: List<Data>) {
    Row {
        work.forEach { data ->

            val context = LocalContext.current


            val paddingModifier = Modifier.padding(10.dp)
            Card(
                elevation = 4.dp,
                border = BorderStroke(1.dp, Color.Red),
                shape = RoundedCornerShape(20.dp),
                modifier = paddingModifier
                    .size(120.dp)
                    .padding(4.dp).clickable {
                        val intent = Intent(context, SmithActivity::class.java)
                        intent.putExtra("id", data.id.toString())
                        context.startActivity(intent)
                    }
                ,
            ) {

                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,) {
                    Image(painter = painterResource(id = R.drawable.shape_1), contentDescription = "", modifier = Modifier.size(40.dp))
                    Text(text = data.name.toString(),color= colorResource(id = R.color.blue), fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))


                }
            }


        }
    }
}

