package com.cyclone.staffapp.domain.repositories.employee

import com.cyclone.staffapp.data.db.DataBase
import com.cyclone.staffapp.domain.entities.EmployeeDB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class EmployeeDataRepo : EmployeeRepo {

  companion object {
    private val instance = EmployeeDataRepo()

    fun getInstance() = instance
  }

  override fun insert(employeeDB: EmployeeDB) {
    DataBase.getDataBase()
      .employeeDAO()
      .insert(employeeDB)
  }

  override fun insertAll(employeeDB: List<EmployeeDB>) {
    DataBase.getDataBase()
      .employeeDAO()
      .insertAll(employeeDB)
  }

  override fun delete(employeeDB: EmployeeDB) {
    DataBase.getDataBase()
      .employeeDAO()
      .delete(employeeDB)
  }

  override fun deleteAll() {
    DataBase.getDataBase()
      .employeeDAO()
      .deleteAll()
  }

  override fun update(employeeDB: EmployeeDB) {
    DataBase.getDataBase()
      .employeeDAO()
      .update(employeeDB)
  }

  override fun getById(id: Long): Observable<EmployeeDB> {
    return DataBase.getDataBase()
      .employeeDAO()
      .getById(id)
      .observeOn(AndroidSchedulers.mainThread())
      .toObservable()
  }

  override fun getAll(): Observable<List<EmployeeDB>> {
    return DataBase.getDataBase()
      .employeeDAO()
      .getAll()
      .observeOn(AndroidSchedulers.mainThread())
      .toObservable()
  }

  override fun getBySpecialty(id: Long): Observable<List<EmployeeDB>> {
    return DataBase.getDataBase()
      .employeeDAO()
      .getBySpecialty(id)
      .observeOn(AndroidSchedulers.mainThread())
      .toObservable()
  }
}