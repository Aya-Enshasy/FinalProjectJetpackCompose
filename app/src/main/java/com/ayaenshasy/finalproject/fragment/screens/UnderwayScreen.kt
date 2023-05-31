package com.ayaenshasy.finalproject.fragment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.model.Data
import com.ayaenshasy.finalproject.model.Post
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel

@Composable
fun UnderwayScreen(liveDataAllWorkModel: AllWorkViewModel){

    Column(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxSize()) {
        UnderwayScreenList(liveDataAllWorkModel.liveDataUnCompleteWork)
        liveDataAllWorkModel.getUnCompleteOrders(LocalContext.current)
    }

}


@Composable
fun UnderwayScreenList(listModel: List<Data>){
    LazyColumn{
        itemsIndexed(items = listModel){
                index, item ->
            UnderwayScreenCard(data = item)
        }
    }
}

@Composable
fun UnderwayScreenCard(data: Data) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 7.dp)) {

        Card(modifier = Modifier.fillMaxWidth() , elevation = 0.dp,  backgroundColor = colorResource(id = R.color.white)
        ) {
            Column(Modifier.fillMaxWidth()) {

                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Text(
                        text = "${"# "+data.id}", color = colorResource(id = R.color.black), fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = data.createdAt.toString(), color = Color.Gray, fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp) ) {

                    Text(text = data.description.toString(), color =  Color.Gray, fontSize = 14.sp,modifier = Modifier.padding(horizontal = 10.dp) )
                    Text(text = data.name.toString(), color = colorResource(id = R.color.blue), fontSize = 14.sp)

                }    }
        }
    }

}
