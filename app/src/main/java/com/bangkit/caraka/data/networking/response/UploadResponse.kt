package com.bangkit.caraka.data.networking.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @field:SerializedName("hasil")
    val hasil: String
)