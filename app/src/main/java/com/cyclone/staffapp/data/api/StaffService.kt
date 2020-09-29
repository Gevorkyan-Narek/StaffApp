package com.cyclone.staffapp.data.api

import com.cyclone.staffapp.data.entities.Response
import io.reactivex.Single
import retrofit2.http.GET

interface StaffService {

    @GET("testTask.json")
    fun loadData(): Single<Response>
}