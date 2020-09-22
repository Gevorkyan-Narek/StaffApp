package com.cyclone.staffapp.network

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("response")
    val person: List<Person>
)