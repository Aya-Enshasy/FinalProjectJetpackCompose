package com.ayaenshasy.finalproject.model

import com.google.gson.annotations.SerializedName


data class CreateOrder(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("message") var message: String? = null,
)