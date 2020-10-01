package com.cyclone.staffapp.domain.repositories

import com.cyclone.staffapp.data.di.RetrofitInstance
import com.cyclone.staffapp.data.entities.Employee
import com.cyclone.staffapp.domain.entities.EmployeeDB
import com.cyclone.staffapp.domain.entities.SpecialtyDB
import com.cyclone.staffapp.domain.repositories.employee.EmployeeDataRepo
import com.cyclone.staffapp.domain.repositories.specialty.SpecialtyDataRepo
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RetrofitDataRepo {
    companion object {

        fun loadData() {

            Observable.just(
                SpecialtyDataRepo.getInstance(),
            )
                .observeOn(Schedulers.io())
                .doOnNext {
                    it.deleteAll()
                }.subscribe()

            Observable.just(
                EmployeeDataRepo.getInstance(),
            )
                .observeOn(Schedulers.io())
                .doOnNext {
                    it.deleteAll()
                }.subscribe()

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

                    SpecialtyDataRepo.getInstance().insertAll(specialtyDB)
                    EmployeeDataRepo.getInstance().insertAll(employeeDB)
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()
        }
    }
}