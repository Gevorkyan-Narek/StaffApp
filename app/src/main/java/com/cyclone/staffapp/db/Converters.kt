package com.cyclone.staffapp.db

import androidx.room.TypeConverter
import java.net.URI
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromString(value: String?): URI? {
        return URI(value)
    }

    @TypeConverter
    fun UriToString(value: URI?): String? {
        return value.toString()
    }

    @TypeConverter
    fun fromList(value: List<Long>): String? {
        return value.joinToString(",")
    }

    @TypeConverter
    fun StringToLong(value: String?): List<Long>? {
//        val list = mutableListOf<Specialty>()
//        value?.split(",")?.map { s ->
//            val mas = s.split("")
//            list.add(Specialty(mas[0].toLong(), mas[1]))
//        }
//        return list

        return value?.split(",")?.map { s -> s.toLong() }
    }
}
