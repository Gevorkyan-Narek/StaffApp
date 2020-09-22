package com.cyclone.staffapp.model

import com.google.gson.annotations.SerializedName

data class Specialty (
    @SerializedName("specialty_id")
    val id: Int,
    @SerializedName("name")
    val name: String
)