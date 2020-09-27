package com.cyclone.staffapp.workers

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.R
import com.cyclone.staffapp.db.DataBase
import com.cyclone.staffapp.db.EmployeeDB
import kotlinx.android.synthetic.main.workers_fragment.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class WorkersFragment : Fragment(R.layout.workers_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val id = arguments!!.getLong("id")
            lateinit var employeeList: List<EmployeeDB>

            Handler {
                val executor = Executors.newSingleThreadExecutor()

                executor.submit {
                    employeeList = DataBase.getDataBase(context!!).employeeDAO().getBySpecialty(id)
                    Log.d("EmployeeList", employeeList.joinToString(",") { employeeDB -> employeeDB.firstName })
                }

                try {
                    executor.shutdown()
                    executor.awaitTermination(10, TimeUnit.SECONDS)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if (!executor.isShutdown) executor.shutdownNow()
                    Log.d("EmployeeList", employeeList.joinToString(",") { employeeDB -> employeeDB.firstName })
                    workersRecycler.adapter = WorkersAdapter(employeeList)
                }

                true
            }.sendEmptyMessage(0)

        }
    }
}