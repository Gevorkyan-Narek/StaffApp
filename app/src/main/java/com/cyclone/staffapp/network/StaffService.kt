package com.cyclone.staffapp.network

import retrofit2.Call
import retrofit2.http.GET

interface StaffService {

    @GET("testTask.json")
    fun getData(): Call<Response>
}