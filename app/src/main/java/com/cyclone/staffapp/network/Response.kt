package com.cyclone.staffapp.network

import com.cyclone.staffapp.model.Person
import com.squareup.moshi.Json

data class Response(
    @Json(name = "response")
    val person: List<Person>
)