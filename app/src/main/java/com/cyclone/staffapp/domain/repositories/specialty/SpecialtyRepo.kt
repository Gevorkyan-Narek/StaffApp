package com.cyclone.staffapp.domain.repositories.specialty

import com.cyclone.staffapp.domain.entities.SpecialtyDB
import io.reactivex.Observable


interface SpecialtyRepo {

    fun getAll(): Observable<List<SpecialtyDB>>

    fun getById(id: Long): Observable<SpecialtyDB>

    fun insert(specialtyDB: SpecialtyDB)

    fun insertAll(listSpecialtyDB: List<SpecialtyDB>)

    fun delete(specialtyDB: SpecialtyDB)

    fun deleteAll()
}