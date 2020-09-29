package com.cyclone.staffapp.data.entities

import android.net.Uri
import com.squareup.moshi.Json
import java.util.*

data class Employee(
    @Json(name = "f_name")
    val firstName: String,
    @Json(name = "l_name")
    val lastName: String,
    @Json(name = "birthday")
    var birthday: Date?,
    @Json(name = "avatr_url")
    val avatarURI: Uri?,
    @Json(name = "specialty")
    val specialty: List<Specialty>
)