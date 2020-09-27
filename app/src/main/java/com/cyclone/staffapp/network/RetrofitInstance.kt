package com.cyclone.staffapp.network

import com.cyclone.staffapp.json_adapters.DateJsonAdapter
import com.cyclone.staffapp.json_adapters.StringJsonAdapter
import com.cyclone.staffapp.json_adapters.URIJsonAdapter
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
            .add(URIJsonAdapter())
            .build()

        private var retrofit = Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        private var service: StaffService? = null

        fun getService(): StaffService {

            if (service == null) {
                service = retrofit.create(StaffService::class.java)
            }

            return service!!
        }
    }
}