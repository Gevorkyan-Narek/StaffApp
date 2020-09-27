package com.cyclone.staffapp.dao

import androidx.room.*
import com.cyclone.staffapp.db.EmployeeDB

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(employeeDB: EmployeeDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(employeeDB: List<EmployeeDB>)

    @Delete
    fun delete(employeeDB: EmployeeDB)

    @Query("delete from employeedb")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(employeeDB: EmployeeDB)

    @Query("select * from employeedb where id = :id")
    fun getById(id: Long): EmployeeDB

    @Query("select * from employeedb")
    fun getAll(): List<EmployeeDB>

    @Query("select * from employeedb where specialty = :id")
    fun getBySpecialty(id: Long): List<EmployeeDB>
}