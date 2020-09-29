package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.repositories.employee.EmployeeDataRepo
import com.cyclone.staffapp.presentation.adapter.SpecialtyAdapter
import com.cyclone.staffapp.presentation.adapter.WorkersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.speciality_fragment.*
import kotlinx.android.synthetic.main.workers_fragment.*

class WorkersFragment : MainView(R.layout.workers_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    override fun getData() {
        val id = arguments!!.getLong("id")
        EmployeeDataRepo.getInstance()
            .getBySpecialty(id)
            .doOnNext {
                Log.d("Workers: Next", it.joinToString(", ") { employeeDB -> employeeDB.firstName })
                workersRecycler.adapter = WorkersAdapter(it)
            }
            .doOnError {
                Log.d("Workers: Error", it.localizedMessage!!)
                it.printStackTrace()
            }
            .subscribe()
    }
}