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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.ayaenshasy.finalproject.R
import com.ayaenshasy.finalproject.navigation1.navigation
import com.ayaenshasy.finalproject.viewModel.CreateOrderViewModel
import com.google.android.gms.location.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class SmithActivity : ComponentActivity() {
    val createOrderViewModel by viewModels<CreateOrderViewModel>()

    private var locationCallback: LocationCallback? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequired = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = getIntent()
        val id = intent.getStringExtra("id")

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val context = LocalContext.current

                // map
                var currentLocation by remember {
                    mutableStateOf(
                        LocationDetails1(
                            0.toDouble(),
                            0.toDouble()
                        )
                    )
                }
                var visible by remember { mutableStateOf(true) }

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        for (lo in p0.locations) {
                            // Update UI with location data
                            currentLocation = LocationDetails1(lo.latitude, lo.longitude)
                        }
                    }
                }

                val permissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                if (permissions.all {
                        ContextCompat.checkSelfPermission(
                            context,
                            it
                        ) == PackageManager.PERMISSION_GRANTED
                    }) {
                    startLocationUpdates()
                } else {
                    ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
                }
                Log.e("latitude", currentLocation.latitude.toString())
                Log.e("longitude", currentLocation.longitude.toString())

                getMapLocation(createOrderViewModel, context, id!!,currentLocation.latitude, currentLocation.longitude)

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                locationRequired = true
                startLocationUpdates()
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationClient?.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        if (locationRequired) {
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let { fusedLocationClient?.removeLocationUpdates(it) }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }


}

data class LocationDetails1(val latitude: Double, val longitude: Double)


@Composable
fun getMapLocation(
    createOrderViewModel: CreateOrderViewModel = viewModel(),
    context: Context,
    id: String, latitude: Double,  longitude: Double) {
    val details = remember { mutableStateOf("") }
    var selectedImageFile by remember { mutableStateOf<File?>(null) }
    var visible by remember { mutableStateOf(true) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val stroke =
        Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))


    val launcher1 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                selectedImageFile = convertUriToFile(context, it)
            }
        }
    )
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        smithTopBar("Smith", navController)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            if (visible) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .drawBehind { drawRoundRect(color = Color.Blue, style = stroke) }
                    .border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { launcher.launch("image/*") }) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile), "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Text(
                            text = "Image Problem",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                        )

                        visible = !visible
                    }

                }
            }
            selectedImageUri?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 16.dp)
                        .clickable { launcher.launch("image/*") }
                )

            }

            val uploadStatus = createOrderViewModel.uploadStatus.collectAsState()


            OutlinedTextField(
                value = details.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { details.value = it },
                label = { Text(text = "Details") },
                placeholder = { Text(text = "More Details About Problem ...") },
            )


            Button(
                onClick = {

                    val intent = Intent(context, LocationActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("details", details.value)
                    intent.putExtra("lat",latitude.toString() )
                    intent.putExtra("lng", longitude.toString())
                    context.startActivity(intent)
//                    Toast.makeText(context, id, Toast.LENGTH_LONG).show();
//                    Toast.makeText(context, details.value, Toast.LENGTH_LONG).show();
//                    Toast.makeText(context, latitude.toString(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(context, longitude.toString(), Toast.LENGTH_LONG).show();

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 17.dp)
                    .height(50.dp)
            )
            {
                Text(text = "Next", color = Color.White)


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

@Composable
fun smithTopBar(text: String,navController:NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text, textAlign = TextAlign.Center, modifier = Modifier
                            .padding(top = 20.dp)
                            .weight(1f), color = Color.White, fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        modifier = Modifier
                            .size(30.dp)
                            .rotate(180f)
                            .clickable {
                                navController.popBackStack()
                            },
                        contentDescription = "notification icon"
                    )
                }


            }

        }

    }
}