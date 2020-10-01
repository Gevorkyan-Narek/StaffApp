package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.usecases.EmployeeUseCase
import com.cyclone.staffapp.presentation.adapter.WorkersAdapter
import kotlinx.android.synthetic.main.workers_fragment.*

class WorkersFragment : MainView(R.layout.workers_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    override fun getData() {
        val id = arguments!!.getLong("specialtyId")
        EmployeeUseCase.getInstance()
            .getAll()
            .doOnNext {
                workersRecycler.adapter =
                    WorkersAdapter(it.filter { employeeDB -> employeeDB.specialty.contains(id) })
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}