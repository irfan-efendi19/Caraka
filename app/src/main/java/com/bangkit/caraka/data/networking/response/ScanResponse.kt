package com.bangkit.caraka.data.networking.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(
    @field:SerializedName("predictedAksara")
    val result: String
)
