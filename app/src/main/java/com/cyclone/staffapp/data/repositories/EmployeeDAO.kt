package com.cyclone.staffapp.data.repositories

import androidx.room.*
import com.cyclone.staffapp.domain.entities.EmployeeDB
import io.reactivex.Flowable
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
    fun getById(id: Long): Flowable<EmployeeDB>

    @Query("select * from employeedb")
    fun getAll(): Flowable<List<EmployeeDB>>
}