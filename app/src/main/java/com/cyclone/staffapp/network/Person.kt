package com.cyclone.staffapp.network

import retrofit2.http.Url

data class Person(
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val avatarUrl: Url,
    val specialty: List<Specialty>
)