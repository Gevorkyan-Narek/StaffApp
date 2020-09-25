package com.cyclone.staffapp.model

import com.squareup.moshi.Json
import java.net.URI
import java.util.*

data class Person(
    @Json(name = "f_name")
    val firstName: String,
    @Json(name = "l_name")
    val lastName: String,
    @Json(name = "birthday")
    var birthday: Date?,
    @Json(name = "avatr_url")
    val avatarUrl: URI?,
    @Json(name = "specialty")
    val specialty: List<Specialty>
)