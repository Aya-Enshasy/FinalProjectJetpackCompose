package com.ayaenshasy.finalproject.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.service.ApiService
import com.ayaenshasy.finalproject.service.ApiService.Companion.apiService
 import com.ayaenshasy.finalproject.ui.theme.FinalProjectTheme
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel
import com.ayaenshasy.finalproject.viewModel.CreateOrderViewModel
import com.google.android.gms.location.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import kotlin.math.ln


class LocationActivity : ComponentActivity() {
    val createOrderViewModel by viewModels<CreateOrderViewModel>()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = getIntent()
        val id =  intent.getStringExtra("id")
        val details =  intent.getStringExtra("details")
        val lat =  intent.getStringExtra("lat")
        val lng =  intent.getStringExtra("lng")


         setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
                val context = LocalContext.current

                MapLocation(createOrderViewModel, context, id!!,details!!,lat!!,lng!!, navController)


            }
        }

    }
}



@Composable
fun MapLocation(createOrderViewModel: CreateOrderViewModel = viewModel(), context: Context,id:String,details:String,lat:String,lng:String,navController:NavController) {
     val detailsAddress = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val authorization = getAuthToken(context)
    var selectedImageFile by remember { mutableStateOf<File?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                selectedImageFile = convertUriToFile(context, it)
            }
        }
    )

    Column(modifier = Modifier.fillMaxSize()
    ) {
        smithTopBar("Location",navController)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            OutlinedTextField(
                value = detailsAddress.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { detailsAddress.value = it },
                label = { Text(text = "Location Details") },
                placeholder = { Text(text = "More Details About Location ...") },
            )

            OutlinedTextField(
                value = phone.value,
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "phone"
                    )
                },
                onValueChange = { phone.value = it },
                label = { Text(text = "Phone") },
                placeholder = { Text(text = "Enter your Phone") },
            )
            Button(
                onClick = {
                    selectedImageFile?.let {
                        val requestFile: RequestBody =
                            it.asRequestBody("image/*".toMediaTypeOrNull())
                        MultipartBody.Part.createFormData("photos", it.name, requestFile)
                    }?.let {
                        if (authorization != null) {
                            createOrderViewModel.uploadImage(
                                image = it,
                                workId = id,
                                details = details,
                                detailsAddress = detailsAddress.value,
                                phone = phone.value,
                                lat =lat,
                                long = lng,
                                authorization = authorization.toString(),
                                navController
                            )
                        }
                        Log.e("ddd",id)
                        Log.e("ddd",details)
                        Log.e("ddd",detailsAddress.value)
                        Log.e("ddd",phone.value)
                        Log.e("ddd",authorization.toString())
                        Log.e("ddd",it.toString())
                    }


                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 17.dp)
                    .height(50.dp)
            )
            {
                Text(text = "Add Order", color = Color.White)


            }


        }

    }
}

private fun convertUriToFile(context: Context, uri: Uri): File {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val outputFile = File(context.cacheDir, "temp_image")
    val outputStream = FileOutputStream(outputFile)

    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()

    return outputFile
}
