package com.cyclone.staffapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.staffapp.db.DataBase
import com.cyclone.staffapp.db.EmployeeDB
import com.cyclone.staffapp.db.SpecialtyDB
import com.cyclone.staffapp.network.Employee
import com.cyclone.staffapp.network.Response
import com.cyclone.staffapp.network.RetrofitInstance
import com.cyclone.staffapp.specialty.SpecialtyFragment
import retrofit2.Call
import retrofit2.Callback
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitInstance.getService()

        retrofit.getData().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Log.d("Response", "start")
                if (response.isSuccessful) {
                    Log.d("Response", "succesful")
                    val body = response.body()!!

                    val employeeList = body.employee
                    val specialtyList =
                        employeeList.flatMap { employee: Employee -> employee.specialty }.distinct()

                    val specialtyDB = specialtyList.map { SpecialtyDB(it.id, it.name) }
                    val employeeDB = employeeList.map { employee ->
                        EmployeeDB(
                            employee.firstName,
                            employee.lastName,
                            employee.birthday,
                            employee.avatarURI,
                            employee.specialty.map { specialty -> specialty.id }
                        )
                    }
                    val db = DataBase.getDataBase(this@MainActivity)
                    Handler {
                        val executor = Executors.newSingleThreadExecutor()

                        executor.submit {
                            db.specialtyDAO().insertAll(specialtyDB)
                            db.employeeDAO().insertAll(employeeDB)
                        }

                        try {
                            executor.shutdown()
                            executor.awaitTermination(10, TimeUnit.SECONDS)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        } finally {
                            if (!executor.isShutdown) executor.shutdownNow()
                        }

                        true
                    }.sendEmptyMessage(0)
                } else {
                    Log.d("Response", "Can't connected")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("Failure", t.localizedMessage!!)
                Log.d("Failure", t.printStackTrace().toString())
            }
        })

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, SpecialtyFragment())
            .commit()
    }

    override fun onStop() {
        super.onStop()

        Handler {

            val executor = Executors.newSingleThreadExecutor()

            executor.submit {
                DataBase.getDataBase(this).employeeDAO().deleteAll()
                DataBase.getDataBase(this).specialtyDAO().deleteAll()
            }

            try {
                executor.shutdown()
                executor.awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                if (!executor.isShutdown) executor.shutdownNow()
            }

            true
        }.sendEmptyMessage(0)
    }
}