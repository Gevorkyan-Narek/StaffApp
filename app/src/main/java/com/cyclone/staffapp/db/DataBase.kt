package com.cyclone.staffapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyclone.staffapp.dao.EmployeeDAO
import com.cyclone.staffapp.dao.SpecialtyDAO

@Database(entities = [EmployeeDB::class, SpecialtyDB::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

    abstract fun employeeDAO(): EmployeeDAO
    abstract fun specialtyDAO(): SpecialtyDAO

    companion object {
        private var instance: DataBase? = null

        fun getDataBase(context: Context): DataBase {
            if (instance != null) return instance!!

            instance = Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "employee"
            ).build()

            return instance!!
        }
    }
}