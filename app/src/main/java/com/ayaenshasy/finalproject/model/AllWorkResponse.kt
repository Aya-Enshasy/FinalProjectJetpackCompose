package com.ayaenshasy.finalproject.model

import com.ayaenshasy.finalproject.model.Data
import com.google.gson.annotations.SerializedName


data class AllWorkResponse(

    @SerializedName("code") var code: Int? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()

)