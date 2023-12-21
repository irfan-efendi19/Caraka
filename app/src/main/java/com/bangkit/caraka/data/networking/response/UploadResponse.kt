package com.bangkit.caraka.data.networking.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(

    @field:SerializedName("status")
    var status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("hasil")
    val hasil: String
)