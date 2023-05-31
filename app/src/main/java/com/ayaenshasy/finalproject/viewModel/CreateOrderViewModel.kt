package com.ayaenshasy.finalproject.viewModel

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ayaenshasy.finalproject.activities.OrderDone
import com.ayaenshasy.finalproject.service.ApiService
import com.example.example.ExampleJson2KtKotlin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class CreateOrderViewModel  : ViewModel(){

    val apiService=ApiService.geInstance()

        private val _uploadStatus = MutableStateFlow<ExampleJson2KtKotlin?>(null)
        val uploadStatus: StateFlow<ExampleJson2KtKotlin?> = _uploadStatus

        fun uploadImage(
            image: MultipartBody.Part,
            workId: String,
            details: String,
            detailsAddress: String,
            phone: String,
            lat: String,
            long: String,
            authorization: String,navController: NavController
        ) {
 //
             val workIdPart = workId.toRequestBody("text/plain".toMediaTypeOrNull())
            val detailsPart = details.toRequestBody("text/plain".toMediaTypeOrNull())
            val detailsAddressPart = detailsAddress.toRequestBody("text/plain".toMediaTypeOrNull())
            val phonePart = phone.toRequestBody("text/plain".toMediaTypeOrNull())
            val latPart = lat.toRequestBody("text/plain".toMediaTypeOrNull())
            val longPart = long.toRequestBody("text/plain".toMediaTypeOrNull())

            viewModelScope.launch {
                try {
                    val response = apiService.uploadImage(
                        image,
                        workIdPart,
                        detailsPart,
                        detailsAddressPart,
                        phonePart,
                        latPart,
                        longPart,
                        authorization
                    )
                    if (response.isSuccessful ) {
                          Log.e("lll",_uploadStatus.value.toString())
                         navController.navigate("orderDone_screen")
                    } else {
                          Log.e("ddd",response.body()?.success.toString())


                    }
                } catch (e: Exception) {
                     Log.e("e",e.message.toString())

                }
            }
        }
    }

