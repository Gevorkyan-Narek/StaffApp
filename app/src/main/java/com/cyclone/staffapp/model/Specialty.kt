package com.cyclone.staffapp.model

import com.squareup.moshi.Json

data class Specialty(
    @Json(name = "specialty_id")
    val id: Int,
    @Json(name = "name")
    val name: String
)