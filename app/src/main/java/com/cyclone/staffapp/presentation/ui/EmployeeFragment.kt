package com.cyclone.staffapp.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.repositories.employee.EmployeeDataRepo
import com.cyclone.staffapp.domain.repositories.specialty.SpecialtyDataRepo
import com.cyclone.staffapp.setImage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.employee_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class EmployeeFragment : MainView(R.layout.employee_fragment) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    override fun getData() {

        val id = arguments!!.getLong("id")
        val specList = mutableListOf<Long>()
        EmployeeDataRepo.getInstance()
            .getById(id)
            .doOnNext {
                name.text = "${it.firstName} ${it.lastName}"

                birthday.text = if (it.birthday == null) "-"
                else SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(it.birthday)

                specList.addAll(it.specialty)

                Log.d(
                    "Employee",
                    "${it.firstName} ${it.lastName} ${it.birthday} ${it.id} ${
                        it.specialty.joinToString(", ")
                    }"
                )

                avatar.setImage(context, it.avatarURI)
            }
            .subscribe()

        SpecialtyDataRepo.getInstance()
            .getAll()
            .doOnNext { specialtyList ->

                Log.d("Specialty", "Start")
                Log.d("Specialty", specList.joinToString(", "))

                specialty.text =
                    specialtyList.filter { specialtyDB ->
                        specList.contains(specialtyDB.id)
                    }.joinToString(", ") { specialtyDB -> specialtyDB.name }

            }.subscribe()
    }
}
