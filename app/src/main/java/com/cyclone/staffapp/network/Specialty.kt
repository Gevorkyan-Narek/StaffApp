package com.cyclone.staffapp.network

import com.squareup.moshi.Json

data class Specialty(
    @Json(name = "specialty_id")
    val id: Long,
    @Json(name = "name")
    val name: String
)