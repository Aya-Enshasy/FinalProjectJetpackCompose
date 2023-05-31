package com.ayaenshasy.finalproject.model

import com.google.gson.annotations.SerializedName

data class UserCredentials(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class AuthToken(@SerializedName("token") val token: String)

data class UserRegister(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("password") val password: String
)
