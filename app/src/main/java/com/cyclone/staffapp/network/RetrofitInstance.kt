package com.cyclone.staffapp.network

import com.cyclone.staffapp.DateJsonAdapter
import com.cyclone.staffapp.StringJsonAdapter
import com.cyclone.staffapp.URLJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitInstance {

    companion object {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .add(StringJsonAdapter())
            .add(URLJsonAdapter())
            .build()

        private var retrofit = Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        private var service: StaffService? = null

        fun getData(): StaffService {

            if (service == null) {
                service = retrofit.create(StaffService::class.java)
            }

            return service!!
        }
    }
}