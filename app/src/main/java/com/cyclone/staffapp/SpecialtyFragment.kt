package com.cyclone.staffapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.network.Response
import com.cyclone.staffapp.network.RetrofitInstance
import kotlinx.android.synthetic.main.speciality_fragment.*
import retrofit2.Call
import retrofit2.Callback

class SpecialtyFragment : Fragment(R.layout.speciality_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitInstance.getData()

        retrofit.getData().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    Log.d("Response", "successful")
                    Log.d("Successful Response", response.message())
                    val list = response.body()!!
                    Storage.persons = list.person
                    Storage.specialty = list.person.flatMap { person -> person.specialty }.distinct()
                    specialtyRecycler.adapter = SpecialtyAdapter(Storage.specialty)

                } else {
                    Log.d("Not successful Response", response.message())
                    Log.d("Not successful Response", response.raw().request().url().toString())
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("Failure", t.localizedMessage!!)
                Log.d("Failure", call.request().body().toString())
            }
        })
    }
}