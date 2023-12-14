package com.bangkit.caraka.data.networking.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

    @field:SerializedName("listStory")
    val listStory: List<ListStoryItem> = emptyList(),

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,
)

data class ListStoryItem(

    @field:SerializedName("photoUrl")
    val photoUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String,
)