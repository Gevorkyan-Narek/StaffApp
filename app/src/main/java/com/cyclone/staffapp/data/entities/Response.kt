package com.cyclone.staffapp.data.entities

import com.squareup.moshi.Json

data class Response(
    @Json(name = "response")
    val employee: List<Employee>
)