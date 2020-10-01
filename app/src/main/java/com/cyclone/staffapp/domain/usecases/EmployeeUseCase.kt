package com.cyclone.staffapp.domain.usecases

import com.cyclone.staffapp.data.db.DataBase
import com.cyclone.staffapp.domain.entities.EmployeeDB
import com.cyclone.staffapp.domain.repositories.EmployeeRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeUseCase : EmployeeRepo {

    companion object {
        private val instance = EmployeeUseCase()

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
      Observable.just(DataBase.getDataBase().employeeDAO())
        .observeOn(Schedulers.io())
        .doOnNext {
          it.delete(employeeDB)
        }.subscribe()
    }

    override fun deleteAll() {
        Observable.just(DataBase.getDataBase().employeeDAO())
            .observeOn(Schedulers.io())
            .doOnNext {
                it.deleteAll()
            }.subscribe()
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
}