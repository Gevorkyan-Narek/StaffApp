package com.cyclone.staffapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object {

        private var retrofit = Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
            .addConverterFactory(GsonConverterFactory.create())
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