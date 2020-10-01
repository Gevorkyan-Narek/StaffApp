package com.cyclone.staffapp.data.network

import com.cyclone.staffapp.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface StaffService {

    @GET("testTask.json")
    fun loadData(): Single<Response>
}