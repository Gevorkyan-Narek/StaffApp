package com.cyclone.staffapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyclone.staffapp.data.repositories.EmployeeDAO
import com.cyclone.staffapp.data.repositories.SpecialtyDAO
import com.cyclone.staffapp.domain.entities.EmployeeDB
import com.cyclone.staffapp.domain.entities.SpecialtyDB

@Database(entities = [EmployeeDB::class, SpecialtyDB::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

    abstract fun employeeDAO(): EmployeeDAO
    abstract fun specialtyDAO(): SpecialtyDAO

    companion object {
        private var instance: DataBase? = null

        fun initDataBase(context: Context) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "employee"
            ).build()

            instance = db
        }

        fun getDataBase() = instance!!
    }
}