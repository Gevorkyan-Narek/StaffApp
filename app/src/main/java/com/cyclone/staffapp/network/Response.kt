package com.cyclone.staffapp.network

import com.squareup.moshi.Json

data class Response(
    @Json(name = "response")
    val employee: List<Employee>
)