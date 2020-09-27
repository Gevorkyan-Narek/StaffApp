package com.cyclone.staffapp.dao

import androidx.room.*
import com.cyclone.staffapp.db.SpecialtyDB

@Dao
interface SpecialtyDAO {

    @Query("select * from specialtydb")
    fun getAll(): List<SpecialtyDB>

    @Query("select * from specialtydb where id = :id")
    fun getById(id: Long): SpecialtyDB

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(specialtyDB: SpecialtyDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(listSpecialtyDB: List<SpecialtyDB>)

    @Delete
    fun delete(specialtyDB: SpecialtyDB)

    @Query("delete from specialtydb")
    fun deleteAll()
}