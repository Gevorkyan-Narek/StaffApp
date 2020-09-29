package com.cyclone.staffapp.domain.repositories.specialty

import com.cyclone.staffapp.data.db.DataBase
import com.cyclone.staffapp.domain.entities.SpecialtyDB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SpecialtyDataRepo : SpecialtyRepo {

    companion object {
        private val instance = SpecialtyDataRepo()

        fun getInstance() = instance
    }

    override fun getAll(): Observable<List<SpecialtyDB>> {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

    override fun getById(id: Long): Observable<SpecialtyDB> {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .getById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

    override fun insert(specialtyDB: SpecialtyDB) {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .insert(specialtyDB)
    }

    override fun insertAll(listSpecialtyDB: List<SpecialtyDB>) {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .insertAll(listSpecialtyDB)
    }

    override fun delete(specialtyDB: SpecialtyDB) {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .delete(specialtyDB)
    }

    override fun deleteAll() {
        return DataBase
            .getDataBase()
            .specialtyDAO()
            .deleteAll()
    }

}