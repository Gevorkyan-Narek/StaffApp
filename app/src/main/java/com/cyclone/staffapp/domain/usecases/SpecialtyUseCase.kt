package com.cyclone.staffapp.domain.usecases

import com.cyclone.staffapp.data.db.DataBase
import com.cyclone.staffapp.domain.entities.SpecialtyDB
import com.cyclone.staffapp.domain.repositories.SpecialtyRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SpecialtyUseCase : SpecialtyRepo {

    companion object {
        private val instance = SpecialtyUseCase()

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
        DataBase.getDataBase()
            .specialtyDAO()
            .insert(specialtyDB)
    }

    override fun insertAll(listSpecialtyDB: List<SpecialtyDB>) {
        DataBase.getDataBase()
            .specialtyDAO()
            .insertAll(listSpecialtyDB)
    }

    override fun delete(specialtyDB: SpecialtyDB) {
        Observable.just(DataBase.getDataBase().specialtyDAO())
            .observeOn(Schedulers.io())
            .doOnNext {
                it.delete(specialtyDB)
            }.subscribe()
    }

    override fun deleteAll() {
        Observable.just(DataBase.getDataBase().specialtyDAO())
            .observeOn(Schedulers.io())
            .doOnNext {
                it.deleteAll()
            }.subscribe()
    }

}