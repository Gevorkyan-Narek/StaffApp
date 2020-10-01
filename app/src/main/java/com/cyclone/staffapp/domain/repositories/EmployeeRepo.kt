package com.cyclone.staffapp.domain.repositories

import com.cyclone.staffapp.domain.entities.EmployeeDB
import io.reactivex.Observable

interface EmployeeRepo {

    fun insert(employeeDB: EmployeeDB)

    fun insertAll(employeeDB: List<EmployeeDB>)

    fun delete(employeeDB: EmployeeDB)

    fun deleteAll()

    fun update(employeeDB: EmployeeDB)

    fun getById(id: Long): Observable<EmployeeDB>

    fun getAll(): Observable<List<EmployeeDB>>
}