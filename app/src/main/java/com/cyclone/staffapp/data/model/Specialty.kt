package com.cyclone.staffapp.data.model

import com.squareup.moshi.Json

data class Specialty(
    @Json(name = "specialty_id")
    val id: Long,
    @Json(name = "name")
    val name: String
)