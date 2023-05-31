package com.ayaenshasy.finalproject.service

import com.ayaenshasy.finalproject.model.*
import com.example.example.ExampleJson2KtKotlin
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.*

interface ApiService {

    @GET("all/works")
    suspend fun getAllWorkResponse(): AllWorkResponse

    @GET("order/pending/user")
    suspend fun getPendingOrders(@Header("Authorization") Authorization: String): AllWorkResponse

    @GET("order/UnComplete/user")//todo
    suspend fun getUnCompleteOrders(@Header("Authorization") Authorization: String): AllWorkResponse

    @GET("order/complete/user")
    suspend fun getCompleteOrders(@Header("Authorization") Authorization: String): AllWorkResponse

    @POST("auth/login/user")
    suspend fun login(@Body credentials: UserCredentials): LoginResponse

    @POST("auth/register/user")
    suspend fun register(@Body userRegister: UserRegister): LoginResponse

    @Multipart
    @POST("create/order")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("work_id") work_id: RequestBody,
        @Part("details") details: RequestBody,
        @Part("details_address") details_address: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("long") long: RequestBody,
        @Header("Authorization") Authorization: String,
    ): Response<ExampleJson2KtKotlin>


    companion object{
        var apiService :ApiService? =null

        fun geInstance():ApiService{
            if (apiService ==null){
                apiService= Retrofit.Builder()
                    .baseUrl("https://studentucas.awamr.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }


    }

}

