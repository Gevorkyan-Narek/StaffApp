package com.cyclone.staffapp.domain.usecases

import com.cyclone.staffapp.data.model.Employee
import com.cyclone.staffapp.data.network.RetrofitInstance
import com.cyclone.staffapp.domain.entities.EmployeeDB
import com.cyclone.staffapp.domain.entities.SpecialtyDB

class RetrofitUseCase {
    companion object {

        fun loadData() {

            SpecialtyUseCase.getInstance().deleteAll()
            EmployeeUseCase.getInstance().deleteAll()

            RetrofitInstance.getService()
                .loadData()
                .doOnSuccess { response ->
                    val employeeList = response.employee
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

                    SpecialtyUseCase.getInstance().insertAll(specialtyDB)
                    EmployeeUseCase.getInstance().insertAll(employeeDB)
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()
        }
    }
}