package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.usecases.EmployeeUseCase
import com.cyclone.staffapp.domain.usecases.SpecialtyUseCase
import com.cyclone.staffapp.getAge
import com.cyclone.staffapp.getYearString
import com.cyclone.staffapp.setImage
import kotlinx.android.synthetic.main.employee_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class EmployeeFragment : MainView(R.layout.employee_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        getData()
    }

    override fun getData() {
        val id = arguments!!.getLong("id")
        val specList = mutableListOf<Long>()
        EmployeeUseCase.getInstance()
            .getById(id)
            .doOnNext {
                name.text = getString(R.string.name, it.firstName, it.lastName)
                birthday.text = getString(
                    R.string.date,
                    if (it.birthday == null) ""
                    else SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(it.birthday),
                    it.birthday.getAge(),
                    getYearString(it.birthday.getAge())
                )
                specList.addAll(it.specialty)
                avatar.setImage(context, it.avatarURI)
            }
            .subscribe()

        SpecialtyUseCase.getInstance()
            .getAll()
            .doOnNext { specialtyList ->
                specialty.text =
                    specialtyList.filter { specialtyDB ->
                        specList.contains(specialtyDB.id)
                    }.joinToString(", ") { specialtyDB -> specialtyDB.name }
            }.subscribe()
    }
}
