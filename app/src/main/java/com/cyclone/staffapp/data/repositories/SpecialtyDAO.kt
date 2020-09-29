package com.cyclone.staffapp.data.repositories

import androidx.room.*
import com.cyclone.staffapp.domain.entities.SpecialtyDB
import io.reactivex.Flowable

@Dao
interface SpecialtyDAO {

    @Query("select * from specialtydb")
    fun getAll(): Flowable<List<SpecialtyDB>>

    @Query("select * from specialtydb where id = :id")
    fun getById(id: Long): Flowable<SpecialtyDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(specialtyDB: SpecialtyDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(listSpecialtyDB: List<SpecialtyDB>)

    @Delete
    fun delete(specialtyDB: SpecialtyDB)

    @Query("delete from specialtydb")
    fun deleteAll()
}